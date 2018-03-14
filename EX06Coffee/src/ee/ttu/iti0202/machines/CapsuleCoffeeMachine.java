package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

import java.util.Optional;

public class CapsuleCoffeeMachine extends CoffeeMachine {
    private Drink.Drinks capsule = null;

    public CapsuleCoffeeMachine(WaterContainer container, String name) {
        super(container, name);
    }

    @Override
    boolean isTrashCollectorFull() {
        return trashCollector >= 10;
    }

    @Override
    public void selectDrink(Drink.Drinks d) {
        capsule = d;
        logger.fine(String.format("Inserted %s capsule into %s", d, name));
    }

    @Override
    public Drink start() throws Exception {
        logger.fine(String.format("Pressed start button on %s", name));
        int waterNeeded;
        if (isTrashCollectorFull()) {
            throw new CoffeeMachineException("CoffeeMachine's trash container is full."
                    + String.format("(machine: %s)", name));
        }
        if (capsule == null) {
            return Drink.factory(Drink.Drinks.WATER, this).get();
        }
        if (!Drink.getWaterReq(capsule).isPresent()) {
            throw new DrinkException("Selected capsule(enum) doesn't exist in Drink.getWaterReq() if else statements.");
        } else {
            waterNeeded = Drink.getWaterReq(capsule).get();
        }
        if (!container.enoughWater(waterNeeded)) {
            throw new WaterContainerException("Not enough water in watercontainer."
                    + String.format("(machine: %s)", name));
        } else {
            Optional<Drink> drinkToBeMade = Drink.factory(capsule, this);
            if (!drinkToBeMade.isPresent()) {
                throw new DrinkException("Selected capsule(enum) doesn't exist in Drink.factory() if else statements.");
            } else {
                capsule = Drink.Drinks.WATER; // water capsule is an empty capsule
                trashCollector += 1;
                container.drainWater(waterNeeded, this);
                return drinkToBeMade.get();
            }
        }
    }

    public Drink start(Drink.Drinks d) throws Exception {
        selectDrink(d);
        return start();
    }
}
