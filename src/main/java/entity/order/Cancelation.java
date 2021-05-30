package entity.order;

public class Cancelation extends State{
    Cancelation(Order context){
        super(context);
    }
    @Override
    void setContext(Order context) {

    }

    @Override
    void doApproval() {

    }

    @Override
    void doHoldOn() {

    }

    @Override
    void doCancel() {
        // Huy bo don hang
    }
}
