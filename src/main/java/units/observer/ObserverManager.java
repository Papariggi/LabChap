package units.observer;


import units.AllUnits;

public interface ObserverManager
{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObs();
}
