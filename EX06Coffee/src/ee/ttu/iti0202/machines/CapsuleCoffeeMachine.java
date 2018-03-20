package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.capsule.Capsule;
import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.drinks.Water;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

import java.util.Optional;

public class CapsuleCoffeeMachine extends CoffeeMachine {
    private Capsule capsule = null;

    public CapsuleCoffeeMachine(WaterContainer container, String name) {
        super(container, name);
    }

    @Override
    boolean isTrashCollectorFull() {
        return trashCollector >= 10;
    }

    @Override
    public void selectDrink(Drink.Drinks d) {
        capsule = new Capsule(d);   // inserts new capsule into machine
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
            if (container.enoughWater(Drink.getWaterReq(Drink.Drinks.WATER).get())) {
                container.drainWater(Drink.getWaterReq(Drink.Drinks.WATER).get(), this);
                return Drink.factory(Drink.Drinks.WATER, this).get(); //doesn't produce trash
            } else {
                throw new WaterContainerException("Not enough water in water container."
                        + String.format("(machine: %s)", name));
            }

        }
        waterNeeded = capsule.waterNeeded();
        if (!container.enoughWater(waterNeeded)) {
            throw new WaterContainerException("Not enough water in water container."
                    + String.format("(machine: %s)", name));
        } else {
            Optional<Drink> drinkToMake = Drink.factory(capsule.useCapsule(), this);
            if (!drinkToMake.isPresent()) {
                throw new DrinkException("Selected capsule(enum) doesn't exist in Drink.factory() if else statements.");
            } else if (drinkToMake.get() instanceof Water) {
                container.drainWater(waterNeeded, this);
                return drinkToMake.get(); //doesn't produce trash
            } else {
                trashCollector += 1;
                container.drainWater(waterNeeded, this);
                return drinkToMake.get();
            }
        }
    }

    public Drink start(Drink.Drinks d) throws Exception {
        selectDrink(d);
        return start();
    }

    public void removeCapsule() {
        if (capsule != null) {
            capsule = null;
        }
    }

    public Capsule getCapsule() {
        return capsule;
    }
}
