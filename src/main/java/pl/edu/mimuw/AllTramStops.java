package pl.edu.mimuw;
import java.util.Arrays;

//In this class, in addition to storing information common to all stops,
//there is an implemented binary search method that, after prior sorting,
//allows finding a stop by its name in logarithmic time. This is particularly
//useful when setting up new tram lines where the simulation only receives the names of the stops.
public class AllTramStops {
    private TramStop [] allStops;
    private TramStop [] allStopsSorted;
    private int passengerLimit;
    public AllTramStops() {}
    public void initialize(int getPassengerLimit, TramStop ... getAllStops){
        passengerLimit = getPassengerLimit;
        allStops = getAllStops;
        allStopsSorted = getAllStops;
        Arrays.sort(allStopsSorted);
    }
    public TramStop getStopByIndex(int index){
        return allStops[index];
    }
    public TramStop getStopNameByIndex(int index){
        return allStops[index];
    }
    public TramStop getStopByName(String stopName, AllTramStops allTramStops){
        int beg = 0, end = allStopsSorted.length - 1;
        while (beg <= end){
            int mid = (beg + end) / 2;
            if( stopName.compareTo(allStopsSorted[mid].getName()) >= 0){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        return allTramStops.getStopNameByIndex(end);
    }

    public void nextDayStops(){
        for(TramStop i : allStops){
            i.nextDayStop();
        }
    }

    public int getPassengerLimit() {
        return passengerLimit;
    }
    public int getNumberOfStops(){
        return allStops.length;
    }

}
