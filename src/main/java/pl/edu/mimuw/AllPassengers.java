package pl.edu.mimuw;

//This is a class that, as the name suggests, stores information about all passengers.
// We can treat it as an office that allows us to receive and store information about
// all participants in the simulation. This is particularly useful when we want to set up
// all the passengers in the simulation.
public class AllPassengers {
    private int countPassengers = 0;
    private Passenger[] allPassengers;
    public AllPassengers(){}
    public void setAllPassengers(Passenger[] getAllPassengers) {
        allPassengers = getAllPassengers;
    }
    public Passenger getPassengerByIndex(int index){
        return allPassengers[index];
    }
    public int getCountPassengers(){
        return countPassengers;
    }
    public void incCountPassengers() {
        countPassengers++;
    }
}
