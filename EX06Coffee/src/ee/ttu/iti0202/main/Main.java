package ee.ttu.iti0202.main;

import ee.ttu.iti0202.kitchen.Kitchen;
import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.machines.CoffeeMachine;
import ee.ttu.iti0202.watercontainer.WaterContainer;

public class Main {
    public static void main(String[] args) {
        GlobalLogger.setupLogger();
        Kitchen kitchen = new Kitchen();
        WaterContainer container1 = new WaterContainer();
        CoffeeMachine coffeeMachine = new CoffeeMachine(container1);
        kitchen.addMachine(coffeeMachine);
        kitchen.addMachine(coffeeMachine);
    }
}
