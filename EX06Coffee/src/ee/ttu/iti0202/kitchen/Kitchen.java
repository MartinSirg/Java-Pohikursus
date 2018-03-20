package ee.ttu.iti0202.kitchen;
import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.KitchenException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.machines.CoffeeMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Kitchen {
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<CoffeeMachine> machines = new ArrayList<>();
    private List<Drink.Drinks> order = new ArrayList<>();
    private Map<CoffeeMachine, List<Drink.Drinks>> specifiedOrder = new HashMap<>();

    public Kitchen() {
        GlobalLogger.setupLogger();
    }

    public List<CoffeeMachine> getMachines() {
        return machines;
    }

    public List<Drink.Drinks> getOrder() {
        return order;
    }

    public Map<CoffeeMachine, List<Drink.Drinks>> getSpecifiedOrder() {
        return specifiedOrder;
    }

    public Kitchen addMachine(CoffeeMachine machine) {
        if (!machines.contains(machine)) {
            machines.add(machine);
            logger.info(String.format("Added %s to kitchen.", machine.getName()));
        } else {
            logger.warning("Tried adding machine, to kitchen, that is already in that kitchen.");
        }
        return this;
    }

    public Kitchen orderAdd(Drink.Drinks drink) {
        logger.finest(String.format("Added %s to default order.", drink));
        order.add(drink);
        return this;
    }

    public Kitchen addSpecifiedOrder(CoffeeMachine machine, Drink.Drinks drink) { // saad valida milline masin valmistab
        if (!machines.contains(machine)) {
            logger.warning("Cant add order: Machine is not in kitchen machines list.");
        } else {
            if (!specifiedOrder.containsKey(machine)) {
                List<Drink.Drinks> newList = new ArrayList<>();
                newList.add(drink);
                specifiedOrder.put(machine, newList);
            } else {
                specifiedOrder.get(machine).add(drink);
            }
            logger.finest(String.format("Added %s by %sto specifiedOrder list.", drink, machine.getName()));
        }
        return this;
    }

    public List<Drink> fillOrder() {    //korda mööda valmistavad masinad
        List<Drink> filledOrder = new ArrayList<>();
        if (machines.size() == 0 || order.size() == 0) {
            return filledOrder; // empty list
        }
        int indexCounter = 0;
        for (Drink.Drinks item : order) {
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
                } catch (Exception e) {
                    logger.severe("Uncaught exception at kitchen fillOrder(). Continuing...");
                    complete = true;
                }
            }
            indexCounter++;
        }
        order.clear();
        return filledOrder;
    }

    public List<Drink> fillSpecifiedOrder() {
        try {
            if (specifiedOrder.size() == 0) {
                throw new KitchenException("No items in specifiedOrder list were found.");
            }
        } catch (KitchenException e) {
            logger.warning(e.getMessage() + "Returning empty List");
            return new ArrayList<>();
        }
        List<Drink> result = new ArrayList<>();
        for (Map.Entry<CoffeeMachine, List<Drink.Drinks>> entry : specifiedOrder.entrySet()) {
            for (Drink.Drinks drink : entry.getValue()) {
                entry.getKey().selectDrink(drink); // selects drink for current machine
                boolean complete = false;
                while (!complete) {
                    try {
                        result.add(entry.getKey().start()); // current machines tries to make drink
                        complete = true;
                    } catch (CoffeeMachineException e) {
                        logger.warning(e.getMessage() + " Trying to empty trash container.");
                        entry.getKey().emptyTrashCollector();
                    } catch (WaterContainerException e) {
                        logger.warning(e.getMessage() + " Trying to fill water container.");
                        entry.getKey().getContainer().fill();
                    } catch (DrinkException e) {
                        logger.severe(e.getMessage() + " Cant make drink. Continuing with order");
                        complete = true;
                    } catch (Exception e) {
                        logger.severe("Uncaught exception at kitchen fillSpecifiedOrder(). Continuing with order");
                        complete = true;
                    }
                }
            }
        }
        specifiedOrder.clear();
        return result;
    }
}
