package ee.ttu.iti0202.watercontainer;

import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.machines.AutomaticCoffeeMachine;
import ee.ttu.iti0202.machines.CapsuleCoffeeMachine;
import ee.ttu.iti0202.machines.CoffeeMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WaterContainerTest {

    private WaterContainer container1, container2, container3, container4;
    private CoffeeMachine coffeeMachine, coffeeMachine2;

    @Before
    public void init() {
        GlobalLogger.setupLogger();
        container1 = new WaterContainer(999);
        container2 = new WaterContainer(3001);
        container3 = new WaterContainer(2000);
        container4 = new WaterContainer();
        coffeeMachine = new CapsuleCoffeeMachine(container4, "TestMachine");
        coffeeMachine2 = new AutomaticCoffeeMachine(container1, "TestMachine2");
    }

    @Test
    public void constructor() {
        assertEquals(1000, container1.getCapacity());
        assertEquals(3000, container2.getCapacity());
        assertEquals(2000, container3.getCapacity());
        assertEquals(1000, container4.getCapacity());
    }

    @Test
    public void drainWater() {
        container4.drainWater(1000, coffeeMachine);
        assertEquals(0, container4.getCapacity());
        container4.drainWater(1, coffeeMachine);
        assertEquals(0, container4.getCapacity());
    }

    @Test
    public void fill() {
        container4.fill();
        assertEquals(1000, container4.getCapacity());
        container4.fill();
        assertEquals(1000, container4.getCapacity());
    }

    @Test
    public void removeAddMachine() {
        assertEquals(1, container4.getMachines().size());
        try {
            container4.removeMachine(coffeeMachine);
        } catch (WaterContainerException e) {
            fail("Tried removing machine, but got exception");
        }

        try {
            container4.removeMachine(coffeeMachine);
            fail("Can't remove a machine, that is not attached to the container");
        } catch (WaterContainerException e) {
            assertEquals("WaterContainerMessage: Tried detaching a machine that isn't attached to this container", e.getMessage());
        }
        try {
            container1.addMachine(coffeeMachine);
        } catch (WaterContainerException e) {
            fail("Couldn't add machine to list");
        }
        assertEquals(2, container1.getMachines().size());
        try {
            container1.addMachine(coffeeMachine2);
            fail("successfully added a machine that is already in list");
        } catch (WaterContainerException e) {
            assertEquals("WaterContainerMessage: Tried attaching a machine to the container that is already attached to it." , e.getMessage());
        }

    }

    @Test
    public void enoughWater() {
        assertEquals(false, container1.enoughWater(1001));
        assertEquals(true, container1.enoughWater(1000));
        assertEquals(true, container1.enoughWater(1));
        assertEquals(false, container1.enoughWater(-1));
        assertEquals(false, container1.enoughWater(0));
    }
}