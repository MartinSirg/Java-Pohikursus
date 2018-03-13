package ee.ttu.iti0202.drinks;


import java.util.Optional;
import java.util.logging.Logger;

public abstract class Drink {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public enum Drinks { COFFEE, CAPPUCCINO, HOT_COCOA, ESPRESSO, WATER }

    public Drink() {
    }

    public static Optional<Integer> getWaterReq(Drinks drink) {
        if (drink == Drinks.COFFEE) {
            return Optional.of(Coffee.WATER);
        } else if (drink == Drinks.ESPRESSO) {
            return Optional.of(Espresso.WATER);
        } else if (drink == Drinks.HOT_COCOA) {
            return Optional.of(HotCocoa.WATER);
        } else if (drink == Drinks.CAPPUCCINO) {
            return Optional.of(Cappuccino.WATER);
        } else if (drink == Drinks.WATER) {
            return Optional.of(Water.WATER);
        }
        return Optional.empty();
    }

    public static Optional<Drink> factory(Drinks drink) {
        if (drink == Drinks.COFFEE) {
            return Optional.of(new Coffee());
        } else if (drink == Drinks.ESPRESSO) {
            return Optional.of(new Espresso());
        } else if (drink == Drinks.HOT_COCOA) {
            return Optional.of(new HotCocoa());
        } else if (drink == Drinks.CAPPUCCINO) {
            return Optional.of(new Cappuccino());
        } else if (drink == Drinks.WATER) {
            return Optional.of(new Water());
        }
        return Optional.empty();
    }
}
