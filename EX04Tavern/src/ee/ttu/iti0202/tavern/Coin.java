package ee.ttu.iti0202.tavern;

import java.util.Objects;

public class Coin {
    private int amount;
    private Currency currency;

    public Coin(int amount, Currency currency) {
        this.currency = currency;
        this.amount = amount;
    }

    public Coin(Currency currency) {
        this.currency = currency; // Coin.Coin(1, currency)??, kuidas kutsuda välja teist konstruktorit?
        this.amount = 1;
    }

    public int getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format("%s %s", amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return amount == coin.amount &&
                Objects.equals(currency, coin.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, currency);
    }
}