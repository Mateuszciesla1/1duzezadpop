package pl.edu.mimuw;

public class EventQueue implements EventQueueInterface{
    private Event[] heap;
    private int size;
    public boolean isEmpty(){
        return size == 0;
    }
    public EventQueue(){
        heap = new Event[1];
        size = 0;
    }
    public void resize(){
        Event[] newHeap = new Event[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }
    public void addNewEvent(Event newEvent){
        if(size == heap.length){
            resize();
        }
        heap[size] = newEvent;
        int current = size++;
        while (current > 0 && heap[current].compareTo(heap[(current-1) / 2]) < 0){
            Event tmp = heap[current];
            heap[current] = heap[(current-1) / 2];
            heap[(current-1) / 2] = tmp;
            current = (current-1) / 2;
        }
    }
    public Event top(){
        assert size > 0;
        return heap[0];
    }
    public Event poll(){
        assert size > 0;
        Event firstEvent = heap[0];
        heap[0] = heap[--size];
        removeRecursion(0);
        return firstEvent;
    }
    private void removeRecursion(int index){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int swapIndex = index;
        if(left < size && heap[left].compareTo(heap[swapIndex]) < 0){
            swapIndex = left;
        }
        if(right < size && heap[right].compareTo(heap[swapIndex]) < 0){
            swapIndex = right;
        }
        if(swapIndex != index){
            Event tmp = heap[index];
            heap[index] = heap[swapIndex];
            heap[swapIndex] = tmp;
            removeRecursion(swapIndex);
        }
    }


}
