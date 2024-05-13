package pl.edu.mimuw;

//This class creates new passengers, sets their nearest stop,
//and determines the time they want to arrive at that stop.
//stopArrival - This variable corresponds to the minute at which they arrived at the stop,
//either by getting off the tram or coming from home
public class Passenger {
    private final int passengerNumber;
    private int ArrivalHour;
    private int stopArrival;
    private final TramStop nearestStop;

    public Passenger(AllTramStops allTramStops, AllPassengers allPassengers){
        passengerNumber = allPassengers.getCountPassengers();
        nearestStop = allTramStops.getStopNameByIndex(Losowanie.losuj(0, allTramStops.getNumberOfStops() - 1));
        allPassengers.incCountPassengers();
    }

    public void nextDayPassenger(){
        chooseHourToArrive();
        stopArrival = ArrivalHour * 60;
    }
    public int getPassengerNumber() {
        return passengerNumber;
    }
    public int getStopArrival(){
        return stopArrival;
    }
    public void setStopArrival(int time){
        stopArrival = time;
    }
    public void chooseHourToArrive(){
        ArrivalHour = Losowanie.losuj(6, 12);
    }
    public int getArrivalHour(){
        return ArrivalHour;
    }
    public TramStop getNearestStop(){
        return nearestStop;
    }
}
