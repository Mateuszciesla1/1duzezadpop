package pl.edu.mimuw;

public interface EventQueueInterface {

    void addNewEvent(Event newElement);
    Event top();
    Event poll();
    boolean isEmpty();
}
