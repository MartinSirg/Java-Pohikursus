package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
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
            System.out.println(waterNeeded);
            System.out.println(container.getCapacity());
            throw new WaterContainerException("Not enough water in water container."
                    + String.format("(machine: %s)", name));
        }

        Optional<Drink> drinkToBeMade = Drink.factory(selectedDrink, this);
        if (!drinkToBeMade.isPresent()) {
            throw new DrinkException("Selected drink(enum) doesn't exist in Drink.factory() if else statements.");
        } else {
            trashCollector += 1;
            container.drainWater(waterNeeded, this);
            return drinkToBeMade.get();
        }
    }
}
