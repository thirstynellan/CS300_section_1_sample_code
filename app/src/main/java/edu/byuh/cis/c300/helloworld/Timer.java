package edu.byuh.cis.c300.helloworld;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

public class Timer extends Handler implements Subject {

    private List<Observer> fans;

    public Timer() {
        fans = new ArrayList<>();//important!
        sendMessageDelayed(obtainMessage(), 100);
    }

    @Override
    public void subscribe(Observer o) {
        fans.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        fans.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (var f : fans) {
            f.update();
        }
    }

    @Override
    public void handleMessage(Message m) {
        notifyObservers();
        sendMessageDelayed(obtainMessage(), 100);
    }
}









