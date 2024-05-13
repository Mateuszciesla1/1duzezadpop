package pl.edu.mimuw;

//Class for Statistics
public class Statistics {
    private int allRidesSimulation;
    private int dayNumber;
    private final int[] allRidesDay;
    private long timeSpentWaiting;
    private final long[] WaitTimeOneDay;
    public Statistics(int numberOfDays){
        allRidesDay = new int[numberOfDays];
        WaitTimeOneDay = new long[numberOfDays];
        allRidesSimulation = 0;
        timeSpentWaiting = 0;
        dayNumber = 1;
    }
    public void nextDay(){
        dayNumber++;
    }
    public void addTimeWaiting(int time){
        allRidesDay[dayNumber - 1]++;
        WaitTimeOneDay[dayNumber - 1] += time;
        allRidesSimulation++;
        timeSpentWaiting += time;
    }

    public void displayStatisticsForOneDay(int getDayNumber){
        System.out.println("    Łączna liczba przejazdów: " + allRidesDay[getDayNumber]);
        System.out.println("    Łączny czas oczekiwania na przystanek: " + WaitTimeOneDay[getDayNumber]);
    }
    public void displayStatistics(){
        System.out.println("    Łączna liczba przejazdów: " + allRidesSimulation);
        double averageTime = 0;
        if(allRidesSimulation != 0)
            averageTime = (double)timeSpentWaiting / (double)allRidesSimulation;
        System.out.println("    Średni czas oczekiwania: " + averageTime);
    }
}
