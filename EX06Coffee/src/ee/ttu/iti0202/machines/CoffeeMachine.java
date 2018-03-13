package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

import java.util.Optional;
import java.util.logging.Logger;

public abstract class CoffeeMachine {
    static final int TWO_HUNDRED = 200;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    int trashCollector = 0;
    WaterContainer container;
    int selected;


    /**
     * Constructor
     *
     * @param container watercontainer object, that will be attatched to machine.
     */
    CoffeeMachine(WaterContainer container) {
        this.container = container;
    }

    public WaterContainer getContainer() {
        return container;
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
    public abstract void selectDrink(Drink.Drinks drinks);

    public abstract Drink start() throws Exception;

}
