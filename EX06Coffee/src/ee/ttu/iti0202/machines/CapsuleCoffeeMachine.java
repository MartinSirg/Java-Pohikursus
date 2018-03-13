package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

public class CapsuleCoffeeMachine extends CoffeeMachine {
    private Drink.Drinks capsule = null;

    public CapsuleCoffeeMachine(WaterContainer container) {
        super(container);
    }

    @Override
    boolean isTrashCollectorFull() {
        return trashCollector >= 10;
    }

    @Override
    public void selectDrink(Drink.Drinks d) {
        capsule = d;
    }

    @Override
    public Drink start() throws Exception {
        int waterNeeded;
        if (isTrashCollectorFull()) {
            throw new CoffeeMachineException("CoffeeMachine's trash container is full.");
        }
        if (capsule == null) {
            return Drink.factory(Drink.Drinks.WATER).get();
        }
        if (!Drink.getWaterReq(capsule).isPresent()) {
            throw new DrinkException("Selected capsule(enum) doesn't exist in Drink.getWaterReq() if else statements.");
        } else {
            waterNeeded = Drink.getWaterReq(capsule).get();
        }
        if (!container.enoughWater(waterNeeded)) {
            throw new WaterContainerException("Not enough water in watercontainer.");
        } else {
            if (!Drink.factory(capsule).isPresent()) {
                throw new DrinkException("Selected capsule(enum) doesn't exist in Drink.factory() if else statements.");
            } else {
                Drink.Drinks drinkToMake = capsule;
                capsule = Drink.Drinks.WATER; // water capsule is an empty capsule
                trashCollector += 1;
                container.drainWater(waterNeeded);
                return Drink.factory(drinkToMake).get();
            }
        }
    }
}
