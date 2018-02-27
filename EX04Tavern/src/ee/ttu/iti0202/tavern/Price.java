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


}
