package ee.ttu.iti0202.drinks;

import ee.ttu.iti0202.machines.CoffeeMachine;

public class HotCocoa extends Drink {
    static final int WATER = 200;

    HotCocoa(CoffeeMachine creator) {
        super(creator);
        logger.info(String.format("Made a cup of Hot Cocoa at %s.", creator.getName()));
    }

    @Override
    public String toString() {
        return "HotCocoa";
    }
}
