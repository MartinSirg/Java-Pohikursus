package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.watercontainer.WaterContainer;

public class AutomaticCoffeeMachine extends CoffeeMachine {

    AutomaticCoffeeMachine(WaterContainer container) {
        super(container);
    }

    /*
    @Override
    boolean makeDrink(Drink drink) {
        // doesnt check for ingredients except water;
        return true;
    }*/
}
