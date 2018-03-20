package ee.ttu.iti0202.watercontainer;

import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.machines.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WaterContainer {
    private static int nextId = 1;
    private static final int LIMIT = 3000;

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private int maxCapacity; // in ml
    private int capacity, id;
    private List<CoffeeMachine> machines = new ArrayList<>();


    /**
     * Constructor1 : creates a water container object with a standard 1000ml capacity
     * assigns it a unique id.
     */
    public WaterContainer() {
        maxCapacity = 1000;
        capacity = maxCapacity;
        id = nextId++;
        logger.info(String.format("Created WaterContainer number %d with a capacity of %d", id, 1000));
    }


    /**
     * Constructor 2: creates a water container object with a custom capacity
     * @param customCapacity has to be between 1000 and 3000 (ml), otherwise throws error and creates a container with a
     *                       capacity closer to the limit. (3500 => 3000, 500 => 1000)
     */
    public WaterContainer(int customCapacity) {
        id = nextId++;
        try {
            if (customCapacity > LIMIT) {
                this.maxCapacity = LIMIT;
                throw new WaterContainerException("Tried creating a container bigger than 3000ml.");
            } else if (customCapacity <= 1000) {
                this.maxCapacity = 1000;
                throw new WaterContainerException("Tried creating a container smaller than 1000ml.");
            } else {
                logger.info(String.format("Created WaterContainer number %d with a capacity of %d", id,
                        customCapacity));
                this.maxCapacity = customCapacity;
                capacity = maxCapacity;
            }
        } catch (WaterContainerException e) {
            capacity = maxCapacity;
            logger.warning(e.getMessage()
                    + String.format(". Created WaterContainer number %d with a capacity of %d", id, maxCapacity));
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<CoffeeMachine> getMachines() {
        return machines;
    }

    /**
     * if water container is not full, fills it
     */
    public void fill() {
        try {
            if (capacity < maxCapacity) {
                capacity = maxCapacity;
                logger.info(String.format("Filled WaterContainer number %d", id));
            } else {
                throw new WaterContainerException("Can't fill a water container that is already full.");
            }
        } catch (WaterContainerException e) {
            logger.info(e.getMessage() + String.format("(WaterContainer number %d)", id));
        }
    }


    /**
     * if there is enough water in container, drains it
     * if not Throws exception
     * @param amount of water to be drained
     */
    public void drainWater(int amount, CoffeeMachine machine) {
        try {
            if (amount > capacity) {
                throw new WaterContainerException(String.format("Not enough water in container number %d", id));
            } else {
                capacity -= amount;
                logger.finer(String.format("Drained %d ml from container number %d attached to %s",
                        amount, id, machine.getName()));
            }
        } catch (WaterContainerException e) {
            logger.severe(e.getMessage());
        }
    }


    /**
     * adds machine to container
     * throws exception if machine is already attatched to this container
     * @param machine coffee machine
     */
    public void addMachine(CoffeeMachine machine) throws WaterContainerException {
        if (machines.contains(machine)) {
            throw new WaterContainerException("Tried attaching a machine to the container that is already attached "
                    + "to it.");
        }  else {
            machines.add(machine);
            logger.info(String.format("Added machine to container number %d", id));
        }
    }

    /**
     * removes machine from container list
     * throws exception, if not in list
     * @param machine coffee machine
     */
    public void removeMachine(CoffeeMachine machine) throws WaterContainerException {
        if (!machines.contains(machine)) {
            throw new WaterContainerException("Tried detaching a machine that isn't attached to this container");
        } else {
            machines.remove(machine);
            logger.info(String.format("Removed machine from container number %d", id));
        }
    }

    public boolean enoughWater(int amount) {
        return amount <= capacity && amount >= 1;
    }

    @Override
    public String toString() {
        return String.format("WaterContainer number %d", id);
    }
}
