package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CoffeeMachine {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        int trashCollector = 0;
        WaterContainer container;
        Map<String, Integer> ingredients = new HashMap<>();
        String name;

        /**
         * Constructor
         * @param container watercontainer object, that will be attatched to machine.
         */
        public CoffeeMachine(WaterContainer container, String name) {
            this.container = container;
            addIngredient("coffee beans", 1000);
            this.name = name;
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
        void emptyTrashCollector() {
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


        /**
         * Adds ingredients to coffee machines ingredient storage.
         * Amount has to be larger than 0
         * Ingredient can't be all spaces or empty string
         * @param ingredient given ingredient
         * @param amount ingredient amount
         */
        void addIngredient(String ingredient, int amount) {
            ingredient = ingredient.toLowerCase();

            try {
                if (amount <= 0) {
                    throw new CoffeeMachineException(String.format("Amount %d is invalid.", amount));
                } else if (ingredient.replace(" ", "").equals("")){
                    throw new CoffeeMachineException(String.format("Ingredient name '%s' is invalid.", ingredient));
                } else {
                    if (ingredients.containsKey(ingredient)) {
                        ingredients.put(ingredient, ingredients.get(ingredient) + amount);
                    } else {
                        ingredients.put(ingredient, amount);
                    }
                    logger.info(String.format("Added %d %s to coffee machines ingredient storage.", amount, ingredient));
                }
            } catch (CoffeeMachineException e) {
                logger.severe(e.getMessage() + " No ingredients were added.");
            }
        }

        /**
         * Adds all the ingredients of a drink to the coffee machines ingredient storage.
         * doesn't add water
         * @param drink drink object
         * @param amount drinks worth of ingredients
         */
        void addIngredient(Drink drink, int amount) {
            Map<String, Integer> reqIngred = drink.getReqIngred();
            for (Map.Entry<String, Integer> entry: reqIngred.entrySet()) {
                if (!entry.getKey().equals("water")) {
                    addIngredient(entry.getKey(), entry.getValue() * amount);
                }
            }
        }

        /**
         * checks whether there are enough ingredients in storage. TODO: make sure this works
         * @param drink given drink.
         * @return
         */
        boolean enoughIngredients(Drink drink) {
            for (Map.Entry<String, Integer> entry: drink.getReqIngred().entrySet()) {
                if (!entry.getKey().equals("water")) {
                    if (ingredients.get(entry.getKey()) < entry.getValue()) {
                        return false;
                    }
                }
            }
            return true;
        }

    /**
     * remove ingredients of drink from ingredient storage
     * @param drink
     * @return
     */
    boolean removeIngredients(Drink drink) {
            if (!enoughIngredients(drink)) {
                return false;
            }
            Map<String, Integer> copy = new HashMap<>(drink.getReqIngred());
            if (copy.containsKey("water")) {
                copy.remove("water");
            }
            for (Map.Entry<String, Integer> entry : copy.entrySet()) {
                ingredients.put(entry.getKey(), ingredients.get(entry.getKey()) - entry.getValue());
            }
            return true;
        }

        /**
         * Makes a drink
         * 1. check if drink is valid
         * 2. check if trash container is full
         * 3. check if there is enough water
         * 4. check if there are enough ingredients
         * @param drink drink
         * @return true if made drink successfully
         */
        public boolean makeDrink(Drink drink) {

            try {
                if (!drink.isValidDrink()) { // not valid drink
                    throw new DrinkException(String.format("Cant make %s because its invalid."
                                    + "(doesn't require milk or water)", drink.getName()));
                } else if (isTrashCollectorFull()) { // trash collector full
                    throw new CoffeeMachineException(String.format("Can't make drink %s because coffee machine's"
                            + "trash collector is full.", drink.getName()));
                } else if (drink.getReqIngred().containsKey("water")
                        && !container.enoughWater(drink.getReqIngred().get("water"))) { // not enough water if requires water
                    throw new WaterContainerException("Not enough water in container.");
                } else if (!enoughIngredients(drink)){
                    throw new CoffeeMachineException("Not enough ingredients in " + name);
                } else {
                    container.drainWater(drink.getReqIngred().get("water"));
                    this.removeIngredients(drink);
                    trashCollector += 1;
                    return true;
                }
            } catch (DrinkException e) {
                logger.severe(e.getMessage() + " Please add water or milk to required ingredients of the drink.");
                return false;
            } catch (CoffeeMachineException e) {
                logger.severe(e.getMessage());
                return false;
            } catch (WaterContainerException e) {
                logger.severe(e.getMessage() + " Please fill water container " + container.toString());
                return false;
            }
        }
        public WaterContainer getContainer() {
            return container;
        }
}
