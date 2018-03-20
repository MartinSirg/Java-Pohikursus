package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.*;
import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.watercontainer.WaterContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CapsuleCoffeeMachineTest {
    private CapsuleCoffeeMachine machine;
    private WaterContainer container;


    @Before
    public void init() {
        GlobalLogger.setupLogger();
        container = new WaterContainer(3000);
        machine = new CapsuleCoffeeMachine(container,"TestCapsMachine");
    }

    @Test
    public void isTrashCollectorFull() {
        assertEquals(false, machine.isTrashCollectorFull());
        for (int i = 0; i < 10; i++) {
            try {
                machine.start(Drink.Drinks.COFFEE);
            } catch (Exception e) {
                fail("No exception expected");
            }
        }
        assertEquals(true, machine.isTrashCollectorFull());
    }

    @Test
    public void removeCapsule() {
        machine.removeCapsule();
        assertEquals(null, machine.getCapsule());
    }

    @Test
    public void selectDrink() {
        machine.removeCapsule();
        machine.selectDrink(Drink.Drinks.COFFEE);
        assertEquals(Drink.Drinks.COFFEE, machine.getCapsule().getContains());
    }

    @Test
    public void start() {
        //regular case: 10 coffees (fill trash container)
        for (int i = 0; i < 10; i++) {
            machine.selectDrink(Drink.Drinks.COFFEE);
            try {
                machine.start();
            } catch (Exception e) {
                fail("No exception expected, line 56 Capsule machine test");
            }
        }// trash bin full case-------------------------------------------------------------------------
        try {
            machine.selectDrink(Drink.Drinks.COFFEE);
            machine.start();
            fail("Should have failed, because trash bin should be full.");
        } catch (Exception e) {
            assertEquals("CoffeeMachineException:" +
                    " CoffeeMachine's trash container is full.(machine: TestCapsMachine)", e.getMessage());
        }
        machine.emptyTrashCollector();
        //empty water container case 1. no capsule------------------------------------------------------
        container.fill();
        container.drainWater(3000, machine);
        machine.removeCapsule();
        try {
            machine.start();
            fail("Should fail, because water container is empty");
        } catch (Exception e) {
            assertEquals("WaterContainerMessage: Not enough water in water container." +
                    "(machine: TestCapsMachine)", e.getMessage());
        }
        // empty water container, machine contains capsule----------------------------------------------
        machine.selectDrink(Drink.Drinks.COFFEE);
        try {
            machine.start();
            fail("Should fail, because water container is empty");
        } catch (Exception e) {
            assertEquals("WaterContainerMessage: Not enough water in water container." +
                    "(machine: TestCapsMachine)", e.getMessage());
        }
        container.fill();
        // capsule contains water(is empty):  doesn't make trash-----------------------------------------
        machine.emptyTrashCollector();
        machine.selectDrink(Drink.Drinks.WATER);
        Drink outcome;
        try {
            outcome = machine.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            outcome = Drink.factory(Drink.Drinks.COFFEE, machine).get();// redundant, so code would compile.
            fail("Machine shouldn't throw exception.");
        }
        assertEquals(0, machine.getTrashCollector());
        assertEquals(Water.class, outcome.getClass());
        // regular case, cappuccino, espresso, Hot cocoa, coffee------------------------------------------
        List<Drink.Drinks> drink = new ArrayList<>();
        drink.add(Drink.Drinks.COFFEE);
        drink.add(Drink.Drinks.CAPPUCCINO);
        drink.add(Drink.Drinks.ESPRESSO);
        drink.add(Drink.Drinks.HOT_COCOA);
        List<Drink> result = new ArrayList<>();
        for (Drink.Drinks d: drink) {
            machine.selectDrink(d);
            try {
                result.add(machine.start());
            } catch (Exception e) {
                fail("No exception expected. Got: " + e.getMessage());
            }
        }
        if (result.size() == 4) {
            assertEquals(Coffee.class,result.get(0).getClass());
            assertEquals(Cappuccino.class,result.get(1).getClass());
            assertEquals(Espresso.class,result.get(2).getClass());
            assertEquals(HotCocoa.class,result.get(3).getClass());
        } else {
            fail("Didn't make 4 drinks");
        }

    }


    @Test
    public void start2() {
        start(); // if start works continue:
        Drink result = Drink.factory(Drink.Drinks.WATER, machine).get();
        try {
            result = machine.start(Drink.Drinks.COFFEE);
        } catch (Exception e) {
            fail("No exception expected. Got: " + e.getMessage());
        }
        assertEquals(Coffee.class, result.getClass());
    }
}