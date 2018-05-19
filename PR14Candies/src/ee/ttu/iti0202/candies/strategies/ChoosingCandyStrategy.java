package ee.ttu.iti0202.candies.strategies;


import ee.ttu.iti0202.candies.candy.Candy;

import java.util.List;
import java.util.Optional;

public interface ChoosingCandyStrategy {
    Optional<Candy> chooseCandy(List<Candy> candies, int age);
}
