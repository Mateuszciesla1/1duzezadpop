package pl.edu.mimuw;

public class Tram extends Vehicles {
    private int thisStop, nextStop;
    private int peopleInsideTram;
    private final int firstStop;
    public Tram(TrackLine getLine, int getFirstStop, AllTrams allTrams) {
        super(allTrams.getNextSideNumber(), getLine);
        allTrams.increaseSideNumber();
        firstStop = getFirstStop;
        nextDayTram();
    }
    public void nextDayTram(){
        thisStop = firstStop;
        if(firstStop == 0)
            nextStop = 1;
        else
            nextStop = firstStop - 1;

        peopleInsideTram = 0;
    }
    public int getPeopleInsideTram(){
        return peopleInsideTram;
    }

    public void moveToNextStation(){
        if(thisStop == nextStop){
            if(nextStop == 0){
                nextStop = 1;
            }
            else{
                nextStop = line.getNumberOfStops() - 2;
            }
        }
        else{
            int add = nextStop - thisStop;
            thisStop = nextStop;
            nextStop += add;
            if(nextStop == line.getNumberOfStops())
                nextStop--;
            if(nextStop == -1)
                nextStop++;
        }
    }
    public void personEnters(){
        peopleInsideTram++;
    }
    public void personExits(){
        peopleInsideTram--;
    }
    public void setTrackLine(TrackLine getLine){
        line = getLine;
    }

    public int getFirstStop() {
        return firstStop;
    }

    public int getThisStop() {
        return thisStop;
    }

    public int getNextStop() {
        return nextStop;
    }
}
