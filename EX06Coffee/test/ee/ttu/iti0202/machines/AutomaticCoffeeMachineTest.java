package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.*;
import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.watercontainer.WaterContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AutomaticCoffeeMachineTest {
    private WaterContainer container;
    private AutomaticCoffeeMachine machine;

    @Before
    public void init() {
        GlobalLogger.setupLogger();
        container = new WaterContainer(3000);
        machine = new AutomaticCoffeeMachine(container, "TestAutoMachine");
    }

    @Test
    public void selectDrink() {
        machine.selectDrink(Drink.Drinks.CAPPUCCINO);
        assertEquals(Drink.Drinks.CAPPUCCINO, machine.getSelectedDrink());
    }

    @Test
    public void start() {
        //regular case: 5 coffees (fill trash container)
        for (int i = 0; i < 5; i++) {
            machine.selectDrink(Drink.Drinks.COFFEE);
            try {
                machine.start();
            } catch (Exception e) {
                fail("No exception expected, got: "  + e.getMessage());
            }
        }// trash bin full case-------------------------------------------------------------------------
        try {
            machine.selectDrink(Drink.Drinks.CAPPUCCINO);
            machine.start();
            fail("Should have failed, because trash bin should be full.");
        } catch (Exception e) {
            assertEquals("CoffeeMachineException:" +
                    " CoffeeMachine's trash container is full.(machine: TestAutoMachine)", e.getMessage());
        }
        machine.emptyTrashCollector();
        //empty water container-------------------------------------------------------------------------
        container.fill();
        container.drainWater(3000, machine);
        try {
            machine.start();
            fail("Should fail, because water container is empty");
        } catch (Exception e) {
            assertEquals("WaterContainerMessage: Not enough water in water container." +
                    "(machine: TestAutoMachine)", e.getMessage());
        }
        container.fill();
        // selected drink is water, doesn't make trash -------------------------------------------------
        machine.selectDrink(Drink.Drinks.WATER);
        machine.emptyTrashCollector();
        try {
            machine.start();
        } catch (Exception e) {
            fail("No exception expected, got: "  + e.getMessage());
        }
        assertEquals(0, machine.getTrashCollector());
        // regular case, cappuccino, espresso, Hot cocoa, coffee------------------------------------------
        List<Drink.Drinks> drinks = new ArrayList<>();
        drinks.add(Drink.Drinks.COFFEE);
        drinks.add(Drink.Drinks.CAPPUCCINO);
        drinks.add(Drink.Drinks.ESPRESSO);
        drinks.add(Drink.Drinks.HOT_COCOA);
        List<Drink> result = new ArrayList<>();
        for (Drink.Drinks drink: drinks) {
            try {
                machine.selectDrink(drink);
                result.add(machine.start());
            } catch (Exception e) {
                fail("No exception expected. Got: " + e.getMessage());
            }
        }
        if (result.size() == 4) {
            assertEquals(Cappuccino.class,result.get(1).getClass());
            assertEquals(Coffee.class,result.get(0).getClass());
            assertEquals(Espresso.class,result.get(2).getClass());
            assertEquals(HotCocoa.class,result.get(3).getClass());
        } else {
            fail("Didn't make 4 drinks");
        }
    }
}