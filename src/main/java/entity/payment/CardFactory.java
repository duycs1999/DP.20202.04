package entity.payment;

import common.interfaces.Card;

public abstract class CardFactory {
    public abstract Card createCreditCard(String cardCode, String owner, String dateExpired, int cvvCode);
}
