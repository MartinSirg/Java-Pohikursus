package ee.ttu.iti0202.card;

import ee.ttu.iti0202.bank.Bank;

import java.math.BigDecimal;

public class BankCard {

    public enum CardType { DEBIT, CREDIT }
    private Bank bank;
    protected BigDecimal balance;

    public Bank getBank() {
        return bank;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal value) {
        balance = balance.add(value);
    }

    public boolean withdraw(BigDecimal value) {
        BigDecimal zero = new BigDecimal(0);
        if (value.compareTo(zero) < 0) { // value < 0
            return false;
        } else {
            balance = balance.subtract(value);
            return true;
        }
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public static BankCard createCard(CardType cardType, Bank bank) {
        BankCard newCard;
        if (cardType == CardType.DEBIT) {
            newCard = new DebitCard();
        } else {
            newCard = new CreditCard();
        }
        newCard.setBank(bank);
        return newCard;
    }
}
