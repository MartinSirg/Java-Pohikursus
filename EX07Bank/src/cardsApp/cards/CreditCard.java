package cardsApp.cards;

import javafx.beans.property.StringProperty;

public class CreditCard extends Card {
    private StringProperty limit;

    CreditCard(String cardNum, String owner, String limit){
        super(cardNum, owner, limit);
    }
}
