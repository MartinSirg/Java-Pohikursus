package ee.ttu.iti0202.card;

import ee.ttu.iti0202.bank.Bank;

import java.math.BigDecimal;

public abstract class BankCard {

    public enum CardType { DEBIT, CREDIT }
    private Bank bank;
    BigDecimal balance;

    public Bank getBank() {
        return bank;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal value) {
        BigDecimal zero = new BigDecimal("0");
        if (value.compareTo(zero) > -1) {
            balance = balance.add(value);
        }
    }

    public abstract boolean withdraw(BigDecimal value);

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
        bank.addCard(newCard);
        return newCard;
    }
}
