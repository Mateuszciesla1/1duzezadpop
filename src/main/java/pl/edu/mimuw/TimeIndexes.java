package pl.edu.mimuw;

//This class is related to events that occur at the same time.
//There is a problem without using this type of class because
//we have a priority queue in the form of a heap, which must order
//events at the same time based on the moment of insertion.
//During the removal, the nodes can behave in various ways, and although
//the heap will be organized according to time, it will not be known
//whether the right or left child was inserted earlier. so we give each event a
//time index, and now we know which event was inserted earlier.
public class TimeIndexes {
    private final long[] Indexes;
    public TimeIndexes(){
        Indexes = new long[24 * 60];
    }
    public long getTimeIndex(int time){
        return Indexes[time]++;
    }
}
