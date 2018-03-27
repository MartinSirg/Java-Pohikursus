package cardsapp.cards;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public  abstract class Card {
    private StringProperty cardNumber, owner, limit;
    public enum Type { DEBIT, CREDIT }

    Card(String cardNumber, String owner, String limit) {
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.owner = new SimpleStringProperty(owner);
        this.limit = new SimpleStringProperty(limit);
    }

    public static Card cardFactory(String cardNumber, String owner, Type type, String limit) {
        if (type == Type.DEBIT) {
            return new DebitCard(cardNumber, owner);
        } else {
            return new CreditCard(cardNumber, owner, limit);
        }
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public StringProperty cardNumberProperty() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public String getOwner() {
        return owner.get();
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }

    public String getLimit() {
        return limit.get();
    }

    public StringProperty limitProperty() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit.set(limit);
    }
}
