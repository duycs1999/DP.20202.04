package entity.order;

public abstract  class State {
    protected Order context;

    void setContext(Order context) {
        this.context = context;
    };
    abstract  void doDefault();
    abstract void doApproval();
    abstract void doHoldOn();
    abstract void doCancel();
}
