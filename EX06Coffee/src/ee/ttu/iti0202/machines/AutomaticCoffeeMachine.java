package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.drinks.Water;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

import java.util.Optional;

public class AutomaticCoffeeMachine extends CoffeeMachine {
    private Drink.Drinks selectedDrink = Drink.Drinks.WATER;

    public AutomaticCoffeeMachine(WaterContainer container, String name) {
        super(container, name);
    }

    public Drink.Drinks getSelectedDrink() {
        return selectedDrink;
    }

    @Override
    public void selectDrink(Drink.Drinks drink) {
        logger.fine(String.format("Selected %s on %s.", drink, name));
        selectedDrink = drink;
    }

    @Override
    public Drink start() throws Exception {
        logger.fine(String.format("Pressed start button on %s", name));
        int waterNeeded;
        if (isTrashCollectorFull()) {
            throw new CoffeeMachineException("CoffeeMachine's trash container is full."
                    + String.format("(machine: %s)", name));
        }
        if (!Drink.getWaterReq(selectedDrink).isPresent()) {
            throw new DrinkException("Selected drink(enum) doesn't exist in Drink.getWaterReq() if else statements.");
        } else {
            waterNeeded = Drink.getWaterReq(selectedDrink).get();
        }
        if (!container.enoughWater(waterNeeded)) {
            throw new WaterContainerException("Not enough water in water container."
                    + String.format("(machine: %s)", name));
        }

        Optional<Drink> drinkToMake = Drink.factory(selectedDrink, this);
        if (!drinkToMake.isPresent()) {
            throw new DrinkException("Selected drink(enum) doesn't exist in Drink.factory() if else statements.");
        } else if (drinkToMake.get() instanceof Water) { // doesn't make trash
            container.drainWater(waterNeeded, this);
            return drinkToMake.get();
        } else {
            container.drainWater(waterNeeded, this);
            trashCollector += 1;
            return drinkToMake.get();
        }
    }
}
