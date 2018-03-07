package ee.ttu.iti0202.card;

import java.math.BigDecimal;

public final class CreditCard extends BankCard {

    protected CreditCard() {
        balance = new BigDecimal("10000");
    }

    @Override
    public boolean withdraw(BigDecimal value) {
        BigDecimal limit = new BigDecimal("-5000"), zero = new BigDecimal(0);

        if (balance.compareTo(limit) < 0 || value.compareTo(zero) < 0) { // balance < -5000 or value < 0
            return false;
        } else {
            balance = balance.subtract(value);
            return true;
        }
    }

    @Override
    public BigDecimal getBalance() {
        BigDecimal zero = new BigDecimal(0);

        if (balance.compareTo(zero) < 0) { // balance < 0
            return zero;
        } else {
            return balance;
        }
    }

    public BigDecimal getDebt() {
        BigDecimal zero = new BigDecimal(0);

        if (balance.compareTo(zero) < 0) { // balance < 0
            return balance.abs();
        } else {
            return zero;
        }
    }
}
