package ee.ttu.iti0202.kitchen;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.machines.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Kitchen {
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<CoffeeMachine> machines = new ArrayList<>();
    private List<Drink.Drinks> order = new ArrayList<>();


    public List<CoffeeMachine> getMachines() {
        return machines;
    }

    public List<Drink.Drinks> getOrder() {
        return order;
    }

    public Kitchen addMachine(CoffeeMachine machine) {
        if (!machines.contains(machine)) {
            machines.add(machine);
        }
        return this;
    }

    public Kitchen orderAdd(Drink.Drinks drink) {
        order.add(drink);
        return this;
    }

    public List<Drink> fillOrder() {
        List<Drink> filledOrder = new ArrayList<>();
        if (machines.size() == 0 || order.size() == 0) {
            return filledOrder; // empty list
        }
        int indexCounter = 0;
        for (Drink.Drinks item: order) {
            int machineIndex = indexCounter % machines.size();
            CoffeeMachine currentMachine = machines.get(machineIndex);
            currentMachine.selectDrink(item);

            boolean complete = false;
            while (!complete) {
                try {
                    filledOrder.add(currentMachine.start());
                    complete = true;
                } catch (CoffeeMachineException e) {
                    logger.warning(e.getMessage() + " Trying to empty trash container.");
                    currentMachine.emptyTrashCollector();
                } catch (WaterContainerException e) {
                    logger.warning(e.getMessage() + " Trying to fill water container.");
                    currentMachine.getContainer().fill();
                } catch (DrinkException e) {
                    logger.severe(e.getMessage() + " Cant make drink. Continuing with order");
                    complete = true;
                } catch (Exception e){
                    logger.severe("Uncaught exception at kitchen fillOrder(). Continuing...");
                    complete = true;
                }
            }
            indexCounter++;
        }
        return filledOrder;
    }
}
