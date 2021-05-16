package entity.payment;

import java.util.List;

/**
 * @author
 */

// code cũ

//public class CreditCard {
//
//    private String cardCode;
//    private String owner;
//    private String dateExpired;
//    protected int cvvCode;
//
//    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
//        this.cardCode = cardCode;
//        this.owner = owner;
//        this.dateExpired = dateExpired;
//        this.cvvCode = cvvCode;
//    }
//}

public class CreditCard implements PaymentStrategy {

    private String cardCode;
    private String owner;
    private String dateExpired;
    protected int cvvCode;

    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }
}



