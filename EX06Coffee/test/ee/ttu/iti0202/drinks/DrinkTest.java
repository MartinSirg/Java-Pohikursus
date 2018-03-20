package ee.ttu.iti0202.drinks;

import ee.ttu.iti0202.logger.GlobalLogger;
import ee.ttu.iti0202.machines.AutomaticCoffeeMachine;
import ee.ttu.iti0202.machines.CoffeeMachine;
import ee.ttu.iti0202.watercontainer.WaterContainer;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class DrinkTest {

    @Test
    public void getWaterReq() {
        int coffee, cappucino, espresso, hotCocoa, water;
        coffee = Drink.getWaterReq(Drink.Drinks.COFFEE).get();
        cappucino = Drink.getWaterReq(Drink.Drinks.CAPPUCCINO).get();
        espresso = Drink.getWaterReq(Drink.Drinks.ESPRESSO).get();
        hotCocoa = Drink.getWaterReq(Drink.Drinks.HOT_COCOA).get();
        water = Drink.getWaterReq(Drink.Drinks.WATER).get();
        assertEquals(200, coffee);
        assertEquals(100, cappucino);
        assertEquals(40, espresso);
        assertEquals(200, hotCocoa);
        assertEquals(200, water);
    }

    @Test
    public void factory() {
        GlobalLogger.setupLogger();
        WaterContainer container = new WaterContainer();
        CoffeeMachine coffeeMachine = new AutomaticCoffeeMachine(container, "TestMachine");
        Optional<Drink> coffee, espresso, cappuccino, hotCocoa, water;
        coffee = Drink.factory(Drink.Drinks.COFFEE, coffeeMachine);
        espresso = Drink.factory(Drink.Drinks.ESPRESSO, coffeeMachine);
        cappuccino = Drink.factory(Drink.Drinks.CAPPUCCINO, coffeeMachine);
        hotCocoa = Drink.factory(Drink.Drinks.HOT_COCOA, coffeeMachine);
        water = Drink.factory(Drink.Drinks.WATER, coffeeMachine);
        if (!(coffee.get() instanceof Coffee)) {fail();}
        if (!(espresso.get() instanceof Espresso)) {fail();}
        if (!(cappuccino.get() instanceof Cappuccino)) {fail();}
        if (!(hotCocoa.get() instanceof HotCocoa)) {fail();}
        if (!(water.get() instanceof Water)) {fail();}
    }
}
