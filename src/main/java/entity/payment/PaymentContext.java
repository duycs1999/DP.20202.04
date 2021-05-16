package entity.payment;

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy){
        this.strategy = strategy;
    }

}
