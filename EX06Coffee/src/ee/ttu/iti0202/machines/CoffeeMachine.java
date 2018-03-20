package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

import java.util.logging.Logger;

public abstract class CoffeeMachine {
    static final int TWO_HUNDRED = 200;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    int trashCollector = 0;
    WaterContainer container;
    String name;


    /**
     * Constructor
     *
     * @param container watercontainer object, that will be attatched to machine.
     */
    CoffeeMachine(WaterContainer container, String name) {
        this.container = container;
        try {
            container.addMachine(this);
        } catch (Exception e) {
            logger.severe(e.getMessage() + " (CoffeeMachine constructor)");
        }
        this.name = name;
    }

    public WaterContainer getContainer() {
        return container;
    }

    public String getName() {
        return name;
    }

    public int getTrashCollector() {
        return trashCollector;
    }

    /**
     * @return true if trash collector is full
     */
    boolean isTrashCollectorFull() {
        return trashCollector >= 5;
    }

    /**
     * empties trash collector
     * if trash collector is already empty, throws exception, catches it and logs it.
     */
    public void emptyTrashCollector() {
        try {
            if (trashCollector == 0) {
                throw new CoffeeMachineException("Cannot empty an empty trash collector.");
            } else {
                trashCollector = 0;
                logger.info("Emptied Coffee machine's trash collector");
            }
        } catch (CoffeeMachineException e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * changes coffee machine's water container.
     *
     * @param newContainer new water container
     */
    public void changeContainer(WaterContainer newContainer) {
        if (container == newContainer) {
            logger.warning("Can't change the container, because new container is the old container.");
        } else {
            try {
                container.removeMachine(this);
            } catch (WaterContainerException e) {
                logger.severe(e.getMessage());
            }
            container = newContainer;
            try {
                newContainer.addMachine(this);
            } catch (WaterContainerException e) {
                logger.severe(e.getMessage());
            }
        }

    }
    public abstract void selectDrink(Drink.Drinks drinks);


    public abstract Drink start() throws Exception;

}
