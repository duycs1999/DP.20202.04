package entity.order;

public class Cancellation extends State{

    @Override
    void doDefault() {
        // Do nothing
    }

    @Override
    void doApproval() {
        // Do nothing
    }

    @Override
    void doHoldOn() {
        // Do nothing
    }

    @Override
    void doCancel() {
        // Cancel Order
        // if (payed) {
        //      return money;
        // } return back;
    }
}
