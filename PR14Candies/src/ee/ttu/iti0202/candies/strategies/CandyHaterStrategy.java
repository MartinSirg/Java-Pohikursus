package ee.ttu.iti0202.candies.strategies;

import ee.ttu.iti0202.candies.candy.Candy;

import java.util.List;
import java.util.Optional;

public class CandyHaterStrategy implements ChoosingCandyStrategy {

    @Override
    public Optional<Candy> chooseCandy(List<Candy> candies, int age) {
        return Optional.empty();
    }
}
