package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.watercontainer.WaterContainer;

public class CapsuleCoffeeMachine extends CoffeeMachine {
    private String currentCapsule;
    private boolean capsuleFull;


    public CapsuleCoffeeMachine(WaterContainer container) {
        super(container);
    }

    public void insertCapsule(String capsule) { // make boolean
        if (!ingredients.containsKey(capsule) || ingredients.get(capsule) <= 0) {
            // TODO: exception, no capsule in ingredient storage
        } else if (currentCapsule != null){
            // TODO: exception, capsule container full
        } else {
            currentCapsule = capsule;
            ingredients.put(capsule, ingredients.get(capsule) - 1);
        }
    }
    /*
    @Override
    void addIngredient(Drink drink, int amount) {
        // TODO: add drinks capsule to ingredient storage * amount;
    }

    @Override
    boolean makeDrink(Drink drink) {
        //check for capsule;
    }*/
}


/*
ingredients.toLOWERCASE */