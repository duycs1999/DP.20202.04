package entity.order;

public class DefaultState extends State {

    @Override
    void doDefault() {
        // Do Payment
    }

    @Override
    void doApproval() {
        // Do nothing
    }

    @Override
    void doHoldOn() {
        setContext(this.context);
        context.changeState(new HoldOn());
    }

    @Override
    void doCancel() {
        setContext(this.context);
        context.changeState(new Cancellation());
        context.cancelOrder();
    }
}
