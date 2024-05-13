package pl.edu.mimuw;

public class TramStop implements Comparable<TramStop> {
    private String name;
    private int peopleAtStop;
    private PassengerQueue queue;

    public TramStop(String getName){
        name = getName;
        queue = new PassengerQueue();
        peopleAtStop = 0;
    }

    public void nextDayStop(){
        queue = new PassengerQueue();
        peopleAtStop = 0;
    }
    public void incPeople(){
        peopleAtStop++;
    }
    public void decPeople(){
        peopleAtStop--;
    }
    public int getPeopleAtStop(){
        return peopleAtStop;
    }
    public void addPerson(Passenger newPassenger){
        queue.push(newPassenger);
        incPeople();
    }
    public void removeFirstPassenger(){
        queue.pop();
        decPeople();
    }
    public Passenger getFirstPassenger(){
        Passenger out = queue.peek();
        removeFirstPassenger();
        return out;
    }
    public String getName(){
        return name;
    }

    @Override
    public int compareTo(TramStop other){
        return name.compareTo(other.getName());
    }
}
