package entity.order;

public class HoldOn extends State{
    HoldOn(Order context){
        super(context);
    }
    @Override
    void setContext(Order context) {

    }

    @Override
    void doHoldOn() {
        //Tiep tuc xu ly
    }

    @Override
    void doCancel() {
        context.changeState(new Cancelation(context));
    }

    @Override
    void doApproval() {
        context.changeState(new Approval(context));
    }


}
