package edu.byuh.cis.c300.helloworld;

public interface Subject {
    public void subscribe(Observer o);
    public void unsubscribe(Observer o);
    public void notifyObservers();
}
