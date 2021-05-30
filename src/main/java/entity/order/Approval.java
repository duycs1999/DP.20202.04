package entity.order;

public class Approval extends State{
    Approval(Order context){
        super(context);
    }
    @Override
    void setContext(Order context) {

    }

    @Override
    void doApproval() {
        context.changeState(new HoldOn(context));
    }

    @Override
    void doHoldOn() {
        // Tiep tuc xu ly
    }

    @Override
    void doCancel() {

    }
}
