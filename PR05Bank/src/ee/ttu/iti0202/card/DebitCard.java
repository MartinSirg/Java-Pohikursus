package ee.ttu.iti0202.card;

import java.math.BigDecimal;

public final class DebitCard extends BankCard {
    DebitCard() {
        super.balance = new BigDecimal(0);
    }


    @Override
    public boolean withdraw(BigDecimal value) {
        BigDecimal zero = new BigDecimal("0");
        if (balance.subtract(value).compareTo(zero) < 0 || value.compareTo(zero) < 0) { //newBalance < 0 or value < 0
            return false;
        } else {
            balance = balance.subtract(value);
            return true;
        }
    }
}
