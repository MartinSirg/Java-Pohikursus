package pub;

import drinks.Beer;
import drinks.Drink;
import drinks.SpecialCocktail;
import drinks.Water;
import meals.Pizza;
import meals.PizzaBuilder;

import java.util.ArrayList;
import java.util.List;

public class Pub {
    private static Drink specialCocktail = new SpecialCocktail();
    private static PizzaBuilder pizzaBuilder = new PizzaBuilder();

    public static Drink orderDrink(Drink.Drinks drink, int amount) {
        if (drink == Drink.Drinks.BEER) {
            return new Beer(amount);
        } else if (drink == Drink.Drinks.WATER) {
            return new Water(amount);
        } else {
            return specialCocktail;
        }
    }

    public static void main(String[] args) throws Exception{
        List<Drink> order = new ArrayList<>();
        order.add(orderDrink(Drink.Drinks.BEER, 500));
        order.add(orderDrink(Drink.Drinks.SPECIAL_COCKTAIL,400));
        pizzaBuilder.setName("BaconPizza");
        pizzaBuilder.addComponents(Pizza.Components.BACON);
        Pizza pizzaOrder = pizzaBuilder.createPizza();
        System.out.println(pizzaOrder + " and " + order);
    }
}
