package ee.ttu.iti0202.machines;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.exceptions.CoffeeMachineException;
import ee.ttu.iti0202.exceptions.DrinkException;
import ee.ttu.iti0202.exceptions.WaterContainerException;
import ee.ttu.iti0202.watercontainer.WaterContainer;

public class AutomaticCoffeeMachine extends CoffeeMachine {

    AutomaticCoffeeMachine(WaterContainer container,String name) {
        super(container, name);
    }


    @Override
     public boolean makeDrink(Drink drink) {
        try {
            if (!drink.isValidDrink()) { // not valid drink
                throw new DrinkException(String.format("Cant make %s because its invalid."
                        + "(doesn't require milk or water)", drink.getName()));
            }  else if (isTrashCollectorFull()) { // trash collector full
                throw new CoffeeMachineException(String.format("Can't make drink %s because coffee machine's"
                        + "trash collector is full.", drink.getName()));
            } else {
                container.drainWater(drink.getReqIngred().get("water"));
                this.removeIngredients(drink);
                trashCollector += 1;
                return true;
            }
        } catch (DrinkException e) {
            logger.severe(e.getMessage() + " Please add water or milk to required ingredients of the drink.");
            return false;
        } catch (CoffeeMachineException e) {
            logger.severe(e.getMessage());
            return false;
        } catch (WaterContainerException e) {
            logger.severe(e.getMessage() + " Please fill water container " + container.toString());
            return false;
        }
    }
}
