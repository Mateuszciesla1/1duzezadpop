package pl.edu.mimuw;

//This class is responsible for the tram line. An important element
// here is the calculation of the distance between two stops on the route,
//which is implemented using prefix sums.
//getTimeSum - gives the time you would need to make an entier loop
public class TrackLine {
    private final int trackNumber;
    private final int numberOfTrams;
    private final int numberOfStops;
    private int timeTillNextTram;
    private final TramStop [] trackLine;
    private final Tram [] tramsOnLine;
    private final int [] distanceTime;

    public TrackLine(int[] getDistanceTime, TramStop[] getTrackLine, Tram[] getTramsOnLine, int getTrackNumber) {
        trackLine = getTrackLine;
        distanceTime = getDistanceTime;
        tramsOnLine = getTramsOnLine;
        numberOfStops = getTrackLine.length;
        numberOfTrams = getTramsOnLine.length;
        trackNumber = getTrackNumber;
        timeTillNextTram = 0;
        for (int j : getDistanceTime) {
            timeTillNextTram += 2 * j;
        }
        for(int i = 1; i < getDistanceTime.length; i++){
            distanceTime[i] = distanceTime[i-1] + distanceTime[i];
        }
        if(getTramsOnLine.length == 0)
            timeTillNextTram = 0;
        else
            timeTillNextTram = timeTillNextTram / getTramsOnLine.length;
    }

    public int calculateTimeDistance(int firstStop, int secondStop){
        if(firstStop == 0 && secondStop == 0){
            return 0;
        }
        if(firstStop == 0 || secondStop == 0)
            return distanceTime[Math.max(firstStop, secondStop) - 1];

        return Math.abs(distanceTime[Math.max(firstStop, secondStop) - 1] - distanceTime[Math.min(secondStop, firstStop) - 1]);

    }
    public int getLoopTime(){
        return distanceTime[numberOfStops - 1] - distanceTime[numberOfStops - 2];
    }
    public int getTimeSum(){
        return distanceTime[numberOfStops - 1] * 2;
    }
    public int getNumberOfStops() {
        return numberOfStops;
    }
    public TramStop getStopByIndex(int index){
        return trackLine[index];
    }
    public Tram getTramByIndex(int index){
        return tramsOnLine[index];
    }

    public int getNumberOfTrams() {
        return numberOfTrams;
    }

    public int getTimeTillNextTram() {
        return timeTillNextTram;
    }

    public int getTrackNumber() {
        return trackNumber;
    }
}
