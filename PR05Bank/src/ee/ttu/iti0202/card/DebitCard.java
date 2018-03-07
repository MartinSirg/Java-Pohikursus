package ee.ttu.iti0202.card;

import java.math.BigDecimal;

public final class DebitCard extends BankCard {
    DebitCard() {
        super.balance = new BigDecimal(0);
    }


    @Override
    public boolean withdraw(BigDecimal value) {
        if (balance.subtract(value).doubleValue() < 0 || value.doubleValue() < 0) {
            return false;
        } else {
            balance = balance.subtract(value);
            return true;
        }
    }
}
