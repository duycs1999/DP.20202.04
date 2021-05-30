package common.interfaces;

public interface State {
    public void cancel();
    public void approve();
    public void payment();
    public void order();

}
