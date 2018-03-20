package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.drinks.Espresso;
import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.watercontainer.WaterContainer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoffeeMachineTest {

    CoffeeMachine machine;
    WaterContainer container, container2;

    @Before
    public void init() {
        GlobalLogger.setupLogger();
        container = new WaterContainer(3000);
        machine = new AutomaticCoffeeMachine(container, "TestCoffeeMachine");
    }

    @Test
    public void isTrashCollectorFull() {
        machine.selectDrink(Drink.Drinks.HOT_COCOA);
        for (int i = 0; i < 5; i++) {
            assertEquals(false, machine.isTrashCollectorFull());
            try {
                machine.start();
            } catch (Exception e) {
                fail("No exception expected. Got: " + e.getMessage());
            }
        }
        assertEquals(true, machine.isTrashCollectorFull());
    }

    @Test
    public void emptyTrashCollector() {
        try {
            machine.selectDrink(Drink.Drinks.ESPRESSO);
            machine.start();
        } catch (Exception e) {
            fail("No exception expected. Got: " + e.getMessage());
        }
        assertEquals(1, machine.getTrashCollector());
        machine.emptyTrashCollector();
        assertEquals(0, machine.getTrashCollector());
    }

    @Test
    public void changeContainer() {
        container2 = new WaterContainer(1000);
        machine.changeContainer(container);
        assertEquals(container, machine.getContainer());
        machine.changeContainer(container2);
        assertEquals(container2, machine.getContainer());

    }
}