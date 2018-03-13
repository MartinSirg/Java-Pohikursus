package ee.ttu.iti0202.main;

import ee.ttu.iti0202.drinks.Drink;
import ee.ttu.iti0202.kitchen.Kitchen;
import ee.ttu.iti0202.machines.AutomaticCoffeeMachine;
import ee.ttu.iti0202.machines.CapsuleCoffeeMachine;
import ee.ttu.iti0202.watercontainer.WaterContainer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WaterContainer container1 = new WaterContainer(), container2 = new WaterContainer();
        AutomaticCoffeeMachine autoMachine = new AutomaticCoffeeMachine(container1);
        CapsuleCoffeeMachine capsuleMachine = new CapsuleCoffeeMachine(container2);
        Kitchen kitchen1 = new Kitchen();
        kitchen1.addMachine(autoMachine).addMachine(capsuleMachine).addMachine(autoMachine);
        System.out.println("Machines in the kitchen: " + kitchen1.getMachines().size()); // 2
        kitchen1.orderAdd(Drink.Drinks.CAPPUCCINO).orderAdd(Drink.Drinks.COFFEE).orderAdd(Drink.Drinks.ESPRESSO);
        System.out.println("Orders on the list: " + kitchen1.getOrder().size()); // 3
        List<Drink> filledOrder = kitchen1.fillOrder();
        for (Drink d: filledOrder) {
            System.out.println(d.getClass()); // Cappucino, Coffee, Espresso
        }


    }
}
