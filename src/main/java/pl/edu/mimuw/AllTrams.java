package pl.edu.mimuw;

//In this class, I hold information common to all trams (this time there is only one),
//and it also allows the simulation to assign the correct side numbers to the trams.
public class AllTrams {
    private int tramsLimit;
    private int nextSideNumber = 0;
    public AllTrams(){}
    public void initialize(int getTramsLimit){
        tramsLimit = getTramsLimit;
    }
    public int getTramsLimit(){
        return tramsLimit;
    }
    public void increaseSideNumber(){
        nextSideNumber++;
    }
    public int getNextSideNumber(){
        return nextSideNumber;
    }
}
