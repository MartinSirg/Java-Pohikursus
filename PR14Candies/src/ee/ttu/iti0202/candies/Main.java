package ee.ttu.iti0202.candies;

import ee.ttu.iti0202.candies.candy.Candy;
import ee.ttu.iti0202.candies.child.Child;
import ee.ttu.iti0202.candies.strategies.CandyHaterStrategy;
import ee.ttu.iti0202.candies.strategies.ChooseOnlySugarFreeCandiesStrategy;
import ee.ttu.iti0202.candies.strategies.ChoosingCandyStrategy;
import ee.ttu.iti0202.candies.strategies.MakeChoiceDependingOnAgeStrategy;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        // Martin likes all candies.
        Child martin = new Child(6, (candies, age) -> {
            if (candies.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(candies.get(random.nextInt(candies.size())));
        });
        Child lisa = new Child(14, new CandyHaterStrategy());

        ChoosingCandyStrategy alwaysFirstStrategy = (candies, age) -> candies.size() == 0 ? Optional.empty() : Optional.of(candies.get(0));

        Child anna = new Child(4, alwaysFirstStrategy);
        Child george = new Child(36, (candies, age) -> {
            for (Candy candy: candies) {
                if (candy.getCandyType().equals(Candy.CandyType.CHOCOLATE_CANDY)) return Optional.of(candy);
            }
            return Optional.empty();
        }); // TODO: Only chocolate candies
        Child gert = new Child(7, new ChooseOnlySugarFreeCandiesStrategy());
        Child anu = new Child(16, new MakeChoiceDependingOnAgeStrategy());
        Child artur = new Child(4, new MakeChoiceDependingOnAgeStrategy());

        List<Candy> candies = new ArrayList<>();

        candies.add(new Candy(Candy.CandyType.CHOCOLATE_CANDY));
        candies.add(new Candy(Candy.CandyType.GUM));
        candies.add(new Candy(Candy.CandyType.GUMMY));
        candies.add(new Candy(Candy.CandyType.JELLY_BEAN));
        candies.add(new Candy(Candy.CandyType.LOLLIPOP));
        candies.add(new Candy(Candy.CandyType.SUGAR_FREE));

        System.out.println(martin.chooseCandy(candies)); // Any
        System.out.println(lisa.chooseCandy(candies)); // Optional.empty
        System.out.println(george.chooseCandy(candies)); // Optional[Candy{candyType=CHOCOLATE_CANDY}]
        System.out.println(gert.chooseCandy(candies)); // Optional[Candy{candyType=SUGAR_FREE}]
        System.out.println(anu.chooseCandy(candies)); // Any
        System.out.println(artur.chooseCandy(candies)); // not gum and not chocolate
    }
}
