package ee.ttu.iti0202.candies.child;


import ee.ttu.iti0202.candies.candy.Candy;
import ee.ttu.iti0202.candies.strategies.ChoosingCandyStrategy;

import java.util.List;
import java.util.Optional;

public class Child {
    private int age;
    private ChoosingCandyStrategy choosingCandyStrategy;
    public Child(int age, ChoosingCandyStrategy choosingCandyStrategy) {
        this.age = age;
        this.choosingCandyStrategy = choosingCandyStrategy;
    }

    public Optional<Candy> chooseCandy(List<Candy> candies) {
        return choosingCandyStrategy.chooseCandy(candies, age);
    }
}
