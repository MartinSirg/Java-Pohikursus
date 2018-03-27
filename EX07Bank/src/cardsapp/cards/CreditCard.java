package cardsapp.cards;

import javafx.beans.property.StringProperty;

class CreditCard extends Card {
    private StringProperty limit;

    CreditCard(String cardNum, String owner, String limit) {
        super(cardNum, owner, limit);
    }
}
