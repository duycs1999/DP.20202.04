package entity.payment;

import common.interfaces.Card;

public class CreditCardFactory extends CardFactory {
    @Override
    public Card createCreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        return new CreditCard(cardCode,owner,dateExpired,cvvCode);
    }
}
