package ee.ttu.iti0202.kitchen;

import ee.ttu.iti0202.drinks.*;
import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.machines.AutomaticCoffeeMachine;
import ee.ttu.iti0202.machines.CapsuleCoffeeMachine;
import ee.ttu.iti0202.machines.CoffeeMachine;
import ee.ttu.iti0202.watercontainer.WaterContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KitchenTest {
    Kitchen kitchen = new Kitchen();
    CoffeeMachine machine1, machine2;
    WaterContainer container;

    @Before
    public void init(){
        container = new WaterContainer(3000);
        machine1 = new CapsuleCoffeeMachine(container, "TestCapsMachine");
        machine2 = new AutomaticCoffeeMachine(container, "TestAutoMachine");
    }


    @Test
    public void addMachine() {
        assertEquals(0, kitchen.getMachines().size());
        kitchen.addMachine(machine1);
        assertEquals(1, kitchen.getMachines().size());
        kitchen.addMachine(machine1);
        assertEquals(1, kitchen.getMachines().size());
        kitchen.addMachine(machine2);
        assertEquals(2, kitchen.getMachines().size());
    }

    @Test
    public void orderAdd() {
        kitchen.orderAdd(Drink.Drinks.COFFEE).orderAdd(Drink.Drinks.CAPPUCCINO).orderAdd(Drink.Drinks.ESPRESSO);
        assertEquals(3, kitchen.getOrder().size());
    }

    @Test
    public void addSpecifiedOrder() {
        addMachine();
        kitchen.addSpecifiedOrder(machine1, Drink.Drinks.CAPPUCCINO).addSpecifiedOrder(machine1, Drink.Drinks.ESPRESSO);
        kitchen.addSpecifiedOrder(machine2, Drink.Drinks.HOT_COCOA).addSpecifiedOrder(machine2, Drink.Drinks.COFFEE);
        assertEquals(2, kitchen.getSpecifiedOrder().size());
        assertEquals(Drink.Drinks.CAPPUCCINO, kitchen.getSpecifiedOrder().get(machine1).get(0));
        assertEquals(Drink.Drinks.ESPRESSO, kitchen.getSpecifiedOrder().get(machine1).get(1));
        assertEquals(Drink.Drinks.HOT_COCOA, kitchen.getSpecifiedOrder().get(machine2).get(0));
        assertEquals(Drink.Drinks.COFFEE, kitchen.getSpecifiedOrder().get(machine2).get(1));

    }

    @Test
    public void fillOrder() {
        List<Drink> emptyResult = new ArrayList<>(), result2, result3;
        assertEquals(emptyResult, kitchen.fillOrder());
        addMachine();
        assertEquals(emptyResult, kitchen.fillOrder());
        orderAdd();
        result2 = kitchen.fillOrder();
        assertEquals("Covfefe by TestCapsMachine", result2.get(0).toString());
        assertEquals("Cappuccino by TestAutoMachine", result2.get(1).toString());
        assertEquals("Espresso by TestCapsMachine", result2.get(2).toString());
        //99 drinks test, must fill with water, and empty machine
        for (int i = 0; i < 33; i++) {
            kitchen.orderAdd(Drink.Drinks.COFFEE).orderAdd(Drink.Drinks.CAPPUCCINO).orderAdd(Drink.Drinks.ESPRESSO);
        }
        result3 = kitchen.fillOrder();
        assertEquals(99, result3.size());

    }

    @Test
    public void fillSpecifiedOrder() {
        List<Drink> emptyResult = new ArrayList<>(), result2, result3;
        assertEquals(emptyResult, kitchen.fillSpecifiedOrder());
        addSpecifiedOrder();
        result2 = kitchen.fillSpecifiedOrder();
        for (Drink d: result2) {
            if (d instanceof Cappuccino) {
                assertEquals("Cappuccino by TestCapsMachine", d.toString());
            } else if (d instanceof Espresso) {
                assertEquals("Espresso by TestCapsMachine", d.toString());
            } else if (d instanceof HotCocoa) {
                assertEquals("HotCocoa by TestAutoMachine", d.toString());
            } else {
                assertEquals("Covfefe by TestAutoMachine", d.toString());
            }
        }
        // 100 drinks, 50 each machine, fills water containers and empties trash bins by itself
        for (int i = 0; i < 50; i++) {
            if (i < 25) {
                kitchen.addSpecifiedOrder(machine1, Drink.Drinks.COFFEE).addSpecifiedOrder(machine1, Drink.Drinks.COFFEE);
            } else {
                kitchen.addSpecifiedOrder(machine2, Drink.Drinks.HOT_COCOA).addSpecifiedOrder(machine2, Drink.Drinks.HOT_COCOA);
            }
        }
        result3 = kitchen.fillSpecifiedOrder();
        assertEquals(100, result3.size());
        for (Drink d: result3) {
            if (d instanceof HotCocoa) {
                assertEquals("HotCocoa by TestAutoMachine", d.toString());
            } else {
                assertEquals("Covfefe by TestCapsMachine", d.toString());
            }
        }

    }
}