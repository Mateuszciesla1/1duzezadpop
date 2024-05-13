package pl.edu.mimuw;

abstract public class Vehicles {
    private final int sideNumber;
    TrackLine line;

    public Vehicles(int getSideNumber, TrackLine getLine){
        sideNumber = getSideNumber;
        line = getLine;
    }

    public int getSideNumber() {
        return sideNumber;
    }

    public TrackLine getLine() {
        return line;
    }
}
