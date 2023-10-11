package com.arcane.Observer.Subject;
import com.arcane.Observer.Observer.Observer;

public interface Subject {
    void register_observer(Observer observer);
    void remove_observer(Observer observer);
    void notify_all_observers();
}
