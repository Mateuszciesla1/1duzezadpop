package pl.edu.mimuw;

//This class is particularly needed to assign the correct track number
//to each newly created track and to set up all the tracks in the simulation.
public class AllTrackLines {
    private TrackLine [] allTrackLines;
    public AllTrackLines(){}
    private int indexOfLastTrack = 0;
    private int nextTrackNumber = 0;
    public void initialize(int getNumberOfTracks){
        allTrackLines = new TrackLine[getNumberOfTracks];
    }
    public void addNewTrack(TrackLine getNewTrack){
        allTrackLines[indexOfLastTrack++] = getNewTrack;
    }

    public int getNextTrackNumber(){
        return nextTrackNumber;
    }
    public void increaseNextTrackNumber(){
        nextTrackNumber++;
    }
    public TrackLine getTrackLineByIndex(int index){
        return allTrackLines[index];
    }
}
