package entity.order;

public class HoldOn extends State{

    @Override
    void doDefault() {
        // Do nothing
    }

    @Override
    void doHoldOn() {
        // Waiting for Admin Approve
    }

    @Override
    void doCancel() {
        setContext(this.context);
        context.changeState(new Cancellation());
        context.cancelOrder();
    }

    @Override
    void doApproval() {
        setContext(this.context);
        context.changeState(new Approval());
        context.approvalOrder();
    }


}
