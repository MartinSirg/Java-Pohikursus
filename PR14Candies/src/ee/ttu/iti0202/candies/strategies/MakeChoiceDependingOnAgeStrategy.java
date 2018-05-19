package ee.ttu.iti0202.candies.strategies;

import ee.ttu.iti0202.candies.candy.Candy;

import java.util.List;
import java.util.Optional;

public class MakeChoiceDependingOnAgeStrategy implements ChoosingCandyStrategy {

    @Override
    public Optional<Candy> chooseCandy(List<Candy> candies, int age) {
        for (Candy candy: candies) {
            if (!(candy.getCandyType().equals(Candy.CandyType.GUM) && age < 5 ||
                    candy.getCandyType().equals(Candy.CandyType.CHOCOLATE_CANDY) && age < 10)) {
                return Optional.of(candy);
            }
        }
        return Optional.empty();
    }
}
