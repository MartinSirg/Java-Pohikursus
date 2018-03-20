package ee.ttu.iti0202.drinks;

import ee.ttu.iti0202.machines.CoffeeMachine;

public class Coffee extends Drink {
    static final int WATER = 200;

    Coffee(CoffeeMachine creator) {
        super(creator);
        logger.info(String.format("Made a cup of covfefe at %s.", creator.getName()));
    }

    @Override
    public String toString() {
        return "Covfefe" + super.toString();
    }
}
