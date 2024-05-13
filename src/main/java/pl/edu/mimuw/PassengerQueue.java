package pl.edu.mimuw;

//Dynamic queue of passengers standing at a given stop

public class PassengerQueue {
    private Passenger [] queue;
    private int front, size, rear;
    public PassengerQueue(){
        queue = new Passenger[1];
        front = 0;
        size = 0;
        rear = -1;
    }
    public void resize(int newSize){
        Passenger []newQueue = new Passenger[newSize];
        for(int i = 0; i < newSize; i++){
            newQueue[i] = queue[(front + i) % queue.length];
        }
        queue = newQueue;
        rear = size - 1;
        front = 0;
    }
    public void push(Passenger newPassenger){
        if(size == queue.length){
            resize(size * 2);
        }
        queue[(rear + 1) % queue.length] = newPassenger;
        rear = (rear + 1) % queue.length;
        size++;
    }
    public void pop(){
        assert size >= 0; //something went wrong
        front = (front + 1) % queue.length;
        size--;
        if(size > 0 && size < queue.length / 2){
            resize(queue.length / 2);
        }
    }

    public Passenger peek(){
        return queue[front];
    }
}
