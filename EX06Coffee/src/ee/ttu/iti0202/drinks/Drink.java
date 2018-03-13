package ee.ttu.iti0202.drinks;

import ee.ttu.iti0202.exceptions.DrinkException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Drink {
    private static int nextId = 1;
    private static final int MIN_AMOUNT = 20;
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Map<String, Integer> reqIngred = new HashMap<>();
    private String capsule, name;


    /**
     * Constructor 1: creates drink with no specified ingredients
     * Thorws exception if name is incorrect and makes a drink with a default name.
     * @param drinkName name of the drink.
     */
    public Drink(String drinkName) {
        try {
            if (drinkName.replace(" ", "").equals("")) {
                throw new DrinkException("Invalid drink name");
            } else {
                capsule = drinkName.toLowerCase() + " capsule";
                name = drinkName.toLowerCase();
                logger.info(String.format("Created %s", drinkName));
            }
        } catch (DrinkException e) {
            capsule = "drink" + nextId;
            name = "drink" + nextId++;
            logger.warning(e.getMessage() + String.format(". Created %s", name));
        }
    }

    /**
     * Constructor 2: creates drink with required ingredients in a map
     * Throws exception if parameter is invalid and makes a drink with default values.
     * @param drinkName name of the drink.
     * @param ingredients Map where key is ingredient and Integer is amount
     */
    public Drink(String drinkName, Map<String, Integer> ingredients) {
        try {
            if (drinkName.replace(" ", "").equals("")) {
                throw new DrinkException("Invalid drink name");
            } else {
                capsule = drinkName.toLowerCase() + " capsule";
                name = drinkName.toLowerCase();
                reqIngred = ingredients;
                logger.info(String.format("Created %s", drinkName));
            }
        } catch (DrinkException e) {
            capsule = "drink" + nextId;
            name = "drink" + nextId++;
            reqIngred = ingredients;
            logger.warning(e.getMessage() + String.format(". Created %s", name));
        }
    }


    /**
     * Adds ingredient to drink's required ingredients.
     * Throws exception, if parameter is invalid.
     * @param ingredient ingredient
     * @param amount amount
     * @return returns this, so methods could be used on top of this method.
     */
    public Drink setIngredient(String ingredient, int amount) {
        ingredient = ingredient.toLowerCase();

        try {
            if (ingredient.replace(" ", "").equals("")) {
                throw new DrinkException("Invalid ingredient name.");
            } else if (amount <= 0) {
                throw new DrinkException("Amount has to be bigger than 0.");
            } else {
                reqIngred.put(ingredient, amount);
                logger.info(String.format("%s now requires %d %s", this.name, amount, ingredient));
                return this;
            }
        } catch (DrinkException e) {
            logger.warning(e.getMessage() + " Nothing was changed");
            return this;
        }
    }


    /**
     * Validates whether drink at least has a water or a milk requirement of 20ml.
     * @return true if drink is valid. false if not.
     */
    public boolean isValidDrink() {
        return reqIngred.getOrDefault("water", 0) >= MIN_AMOUNT || reqIngred.getOrDefault("milk", 0) >= MIN_AMOUNT;
    }

    public Map<String, Integer> getReqIngred() {
        return reqIngred;
    }

    public String getCapsuleName() {
        return capsule;
    }

    public String getName() {
        return name;
    }
}
