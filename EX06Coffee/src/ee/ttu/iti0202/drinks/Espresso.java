package ee.ttu.iti0202.drinks;

import ee.ttu.iti0202.machines.CoffeeMachine;

public class Espresso extends Drink {
    static final int WATER = 40;

    Espresso(CoffeeMachine creator) {
        super(creator);
        logger.info(String.format("Made a cup of espresso at %s.", creator.getName()));
    }

    @Override
    public String toString() {
        return "Espresso";
    }
}
