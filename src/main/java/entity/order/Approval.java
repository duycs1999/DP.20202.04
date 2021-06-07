package entity.order;

public class Approval extends State{

    @Override
    void doDefault() {
        // Do nothing
    }

    @Override
    void doApproval() {
        // Approve Order
    }

    @Override
    void doHoldOn() {
        // Do nothing
    }

    @Override
    void doCancel() {
        // Do nothing
    }
}
