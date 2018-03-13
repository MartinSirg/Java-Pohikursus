package ee.ttu.iti0202.kitchen;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.KitchenException;
import ee.ttu.iti0202.machines.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Kitchen {
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final int FIFTY = 50, TWO_HUNDRED = 200;
    private List<Drink> drinks = new ArrayList<>();
    private List<CoffeeMachine> machines = new ArrayList<>();

    public Kitchen() {
        // define default drinks
        Drink cappucino = new Drink("cappucino"), coffee = new Drink("coffee"), cocoa = new
                Drink("cocoa"), greenTea = new Drink("green tea");

        cappucino.setIngredient("milk", 100).setIngredient("coffee beans", FIFTY).
                setIngredient("water", 100);
        coffee.setIngredient("coffee beans", FIFTY).setIngredient("water", TWO_HUNDRED);
        cocoa.setIngredient("cocoa powder", FIFTY).setIngredient("milk", TWO_HUNDRED);
        greenTea.setIngredient("water", TWO_HUNDRED).setIngredient("green teabag", 1);

        drinks.add(coffee);
        drinks.add(cappucino);
        drinks.add(cocoa);
        drinks.add(greenTea);
    }

    public boolean addMachine(CoffeeMachine machine) {
        if (machines.contains(machine)) {
            try {
                throw new KitchenException("Machine already in list");
            } catch (KitchenException e) {
                logger.warning(e.getMessage());
            }
            return false;
        } else {
            machines.add(machine);
            logger.info("Added coffee machine to kitchen machines list");
            return true;
        }
    }


    /*
    public boolean makeDrinks(Drink drink, CoffeeMachine machine) {
        try {
            machine.makeDrink();
        } catch ()
    }*/

}
