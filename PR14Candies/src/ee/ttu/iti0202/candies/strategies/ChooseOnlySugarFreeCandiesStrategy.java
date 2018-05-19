package ee.ttu.iti0202.candies.strategies;

import ee.ttu.iti0202.candies.candy.Candy;

import java.util.List;
import java.util.Optional;

public class ChooseOnlySugarFreeCandiesStrategy implements ChoosingCandyStrategy {

    @Override
    public Optional<Candy> chooseCandy(List<Candy> candies, int age) {
        for (Candy candy: candies) {
            if (candy.getCandyType().equals(Candy.CandyType.SUGAR_FREE)) return Optional.of(candy);
        }
        return Optional.empty();
    }
}
