package entity.order;

public abstract  class State {
    protected Order context;
    State(Order context){
        this.context = context;
    }
    abstract void setContext(Order context);
    abstract void doApproval();
    abstract void doHoldOn();
    abstract void doCancel();
}
