package ee.ttu.iti0202.capsule;


import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.DrinkException;

public class Capsule {
    private static final int TWO_HUNDRED = 200;
    private Drink.Drinks contains;

    public Capsule(Drink.Drinks drink) {
        contains = drink;
    }

    public Drink.Drinks useCapsule() {
        if (contains == null) {
            return Drink.Drinks.WATER;
        } else {
            Drink.Drinks result = contains;
            contains = null;
            return result;
        }
    }

    public int waterNeeded() throws DrinkException {
        if (contains == null) {
            return TWO_HUNDRED;
        } else {
            if (!Drink.getWaterReq(contains).isPresent()) {
                throw new DrinkException("No such drink found in Drink.getWaterReq method");
            } else {
                return Drink.getWaterReq(contains).get();
            }
        }
    }

    public Drink.Drinks getContains() {
        return contains;
    }
}
