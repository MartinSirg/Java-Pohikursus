package ee.ttu.iti0202.drinks;

import ee.ttu.iti0202.machines.CoffeeMachine;

public class Water extends Drink {
    static final int WATER = 200;

    Water(CoffeeMachine creator) {
        super(creator);
        logger.info(String.format("Poured a cup of water at %s.", creator.getName()));
    }

    @Override
    public String toString() {
        return "Water";
    }
}
