package ee.ttu.iti0202.tavern;

import java.util.*;

public class Price {
    private int priceInBase;
    private Map<Currency, Integer> price = new HashMap<>();

    public Price(int amount, Currency currency) {
        priceInBase = Currency.getRate(currency) * amount;
        price.put(currency, amount);
    }

    public int getPriceInBase() {
        return priceInBase;
    }

    public Price add(int amount, Currency currency) {
        priceInBase += Currency.getRate(currency) * amount;
        price.put(currency, amount);
        return this;
    }

    public Map<Currency, Integer> getPrice() {
        Map<Currency, Integer> result = new HashMap<>();
        result.put(Currency.get("gold"), priceInBase / 100);
        result.put(Currency.get("silver"), (priceInBase % 100) / 10);
        result.put(Currency.get("copper"), (priceInBase % 100) % 10);
        if (result.get(Currency.get("gold")) == 0) result.remove(Currency.get("gold"));
        if (result.get(Currency.get("silver")) == 0) result.remove(Currency.get("silver"));
        if (result.get(Currency.get("copper")) == 0) result.remove(Currency.get("copper"));
        return result;
    }

    public static Price of(int gold, int silver, int copper) {
        Price hind = new Price(copper, Currency.get("copper"));
        hind.add(silver, Currency.get("silver")).add(gold, Currency.get("gold"));
        return hind;
    }

    public static Price of(int copper) {
        return new Price(copper, Currency.get("copper"));
    }

    @Override
    public String toString() {
        Map<Currency, Integer> price = this.getPrice();
        StringBuilder s = new StringBuilder();
        if (price.get(Currency.get("gold")) != null) s.append(price.get(Currency.get("gold")) + " gold");
        String comma = "";
        if (s.toString().length() > 0) {
            comma = ", ";
        }
        if (price.get(Currency.get("silver")) != null) s.append(String.format("%s%d silver", comma,
                price.get(Currency.get("silver"))));
        6
    }
}
