package ee.ttu.iti0202.tavern;

import java.util.*;

/**
 * Keeps track of currencies.
 */
public class Currency {
    /**
     * Holds all the currency instances by the name.
     * Only one currency instance with the given name can exist.
     */
    private static Map<String, Currency> currencies = new HashMap<>();
    /**
     * Reference to the base currency.
     */
    private static Currency baseCurrency;

    /**
     * Name of the currency. E.g. "silver".
     */
    private String name;
    /**
     * Rate compared to base currency.
     * E.g. "silver" to "copper" is 10.
     */
    private int rate;

    /**
     * Instantiate a new currency. Can be called only from the class itself (due to private visibility).
     *
     * @param name Name of the currency.
     * @param rate Rate to the base currency.
     */
    private Currency(String name, int rate) {
        // cannot instantiate from outside
        this.name = name;
        this.rate = rate;
    }

    /**
     * {@code rate} defaults to 1.
     *
     * @see Currency#Currency(String, int)
     */
    private Currency(String name) {
        this(name, 1);
    }

    /**
     * Returns the name of the currency.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rate of the currency to the base currency.
     *
     * @return Rate to base currency.
     */


    public int getRate() {
        return rate;
    }

    /**
     * Adds a currency with the given name and rate to base currency.
     * If the currency by the name already exists, does nothing.
     * Otherwise instantiates a new Currency object and stores it in the map.
     *
     * @param currency Name of the currency.
     * @param rateToBaseCurrency Rate to base currency.
     */
    public static void add(String currency, int rateToBaseCurrency) {
        if (currencies.get(currency) != null) return; // cannot add if already exists, should throw exception?
        currencies.put(currency, new Currency(currency, rateToBaseCurrency));
        if (rateToBaseCurrency == 1) baseCurrency = currencies.get(currency);
    }

    /**
     * {@code rateToBaseCurrency} default to 1.
     *
     * @see Currency#add(String, int)
     */
    public static void add(String currency) {
        add(currency, 1);
    }

    /**
     * Returns all the currencies which are available.
     *
     * @return List of currencies.
     */
    public static List<Currency> getCurrencies() {
        return new ArrayList<Currency>(currencies.values());
    }

    /**
     * Returns the instance of a currency based on the currency name.
     * There can be only one instance of each currency.
     * Only currencies which are added using add() method will be available.
     *
     * @param currency Name of the currency.
     * @return Currency by the name, null if currency is not initialized.
     */
    public static Currency get(String currency) {
        return currencies.getOrDefault(currency, null);
    }

    /**
     * Returns the base currency. Every other currency depends on the base currency.
     *
     * @return The base currency.
     */
    public static Currency getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Returns the rate converting from the given currency to the base currency.
     *
     * @param fromCurrency Currency to convert from.
     * @return The rate.
     */
    public static int getRate(Currency fromCurrency) {
        return getRate(fromCurrency, baseCurrency);
    }

    public static int getRate(Currency fromCurrency, Currency toCurrency) {
        int from = fromCurrency.getRate();
        int to = toCurrency.getRate();
        return from / to;
    }

    /**
     * Reset the whole currency state.
     */
    public static void reset() {
        currencies = new HashMap<>();
        baseCurrency = null;
    }

    @Override
    public String toString() {
        return name;
    }
}