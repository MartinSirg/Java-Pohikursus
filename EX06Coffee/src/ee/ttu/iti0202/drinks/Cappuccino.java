package ee.ttu.iti0202.drinks;

import ee.ttu.iti0202.machines.CoffeeMachine;

public class Cappuccino extends Drink {
    static final int WATER = 100;

    Cappuccino(CoffeeMachine creator) {
        super(creator);
        logger.info(String.format("Made a cup of cappuccino at %s.", creator.getName()));
    }

    @Override
    public String toString() {
        return "Cappuccino" + super.toString();
    }
}
