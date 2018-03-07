package ee.ttu.iti0202.bank;

import ee.ttu.iti0202.card.BankCard;
import ee.ttu.iti0202.card.DebitCard;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<BankCard> allCards = new ArrayList<>();
    private List<BankCard> allDebitCards = new ArrayList<>();
    private List<BankCard> allCreditCards = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(BankCard card) {
        if (card.getBank() == null) {
            card.setBank(this);
            if (card instanceof DebitCard) {
                allDebitCards.add(card);
            } else {
                allCreditCards.add(card);
            }
            allCards.add(card);
        }
    }

    public List<BankCard> getAllCards() {
        return allCards;
    }

    public List<BankCard> getAllDebitCards() {
        return allDebitCards;
    }

    public List<BankCard> getAllCreditCards() {
        return allCreditCards;
    }

    @Override
    public String toString() {
        return String.format("Bank[%s]: %d cards.", name, allCards.size());
    }
}
