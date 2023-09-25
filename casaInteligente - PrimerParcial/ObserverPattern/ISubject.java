package ObserverPattern;

public interface ISubject {
    void registerObserver(IObserver IObserver);

    void removeObserver(IObserver IObserver);

    void notifyObserver();
}
