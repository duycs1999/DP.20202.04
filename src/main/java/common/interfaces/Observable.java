package common.interfaces;

/**
 * @author
 */
public interface Observable {

    void attach(Observer observer);
    void remove(Observer observer);
    void notifyObservers();

}
