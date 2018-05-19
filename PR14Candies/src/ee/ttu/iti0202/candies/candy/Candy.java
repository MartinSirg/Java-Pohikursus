package ee.ttu.iti0202.candies.candy;


public class Candy {
    public enum CandyType {
        LOLLIPOP, JELLY_BEAN, GUMMY, GUM, CHOCOLATE_CANDY, SUGAR_FREE;
    }
    private CandyType candyType;

    public Candy(CandyType candyType) {
        this.candyType = candyType;
    }

    public CandyType getCandyType() {
        return candyType;
    }

    @Override
    public String toString() {
        return "Candy{" +
                "candyType=" + candyType +
                '}';
    }
}
