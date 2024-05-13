package pl.edu.mimuw;

import java.util.Scanner;

//Class related to the simulation; non-obvious things are explained below.
public class Simulation {
    private int numberOfDays;
    private int dayNumber = 1;
    private int numberOfPassengers;
    private int numberOfTracks;
    private int numberOfStops;

    TimeIndexes timeIndexes;

    private final EventQueue eventQueue;
    private Statistics simulationStatistics;

    //Variables with the 'all' prefix could have been implemented in two ways:
    //The first was for them to be classes with static variables and methods,
    //as instances of the class are not needed, and although such a solution
    //would allow the simulation to be conducted in a similar way, it unfortunately
    //has a drawback related to the fact that such 'static' classes are easily
    //manipulated by users, leading to undesirable situations.
    //Therefore, the implemented solution here creates class instances that are
    //inaccessible outside the simulation. In this way, the user only has access to data loading.
    //Moreover, now it is also possible to create several simulations simultaneously, not just one.
    private final AllPassengers allPassengers;
    private final AllTrackLines allTrackLines;
    private final AllTrams allTrams;
    private final AllTramStops allTramStops;
    public Simulation(){
        eventQueue = new EventQueue();
        allPassengers = new AllPassengers();
        allTrackLines = new AllTrackLines();
        allTrams = new AllTrams();
        allTramStops = new AllTramStops();
    }

    public void readData(){
        Scanner sc = new Scanner(System.in);
        int simulationDays = sc.nextInt();
        setNumberOfDays(simulationDays);
        simulationStatistics = new Statistics(simulationDays);

        int stopLimit = sc.nextInt();
        int numberOfStops = sc.nextInt();

        String [] stopsNames = new String[numberOfStops];
        for(int i = 0; i < numberOfStops; i++){
            stopsNames[i] = sc.next();
        }
        setStops(stopLimit, numberOfStops, stopsNames);

        int numberOfPassengers = sc.nextInt();
        setPassengers(numberOfPassengers);

        int tramLimit = sc.nextInt();
        int getTracks = sc.nextInt();
        int[] saveTrackLengths =  new int [getTracks];
        int[] saveTrackTrams = new int[getTracks];
        DataArray [] saveLines = new DataArray[getTracks];
        setNumberOfTracks(getTracks);

        allTrackLines.initialize(getTracks);
        allTrams.initialize(tramLimit);

        for(int i = 0; i < getTracks; i++){
            int numberOfTrams = sc.nextInt();
            int trackLength = sc.nextInt();
            int [] travelTime = new int[trackLength];
            String [] trackStops = new String[trackLength];

            saveTrackLengths[i] = trackLength;
            saveTrackTrams[i] = numberOfTrams;
            saveLines[i] = new DataArray(trackLength);

            for(int j = 0; j < trackLength; j++){
                trackStops[j] = sc.next();
                travelTime[j] = sc.nextInt();

                saveLines[i].setIndex(j, trackStops[j]);
                saveLines[i].setIndex(j, travelTime[j]);

            }
            addLineToSimulation(numberOfTrams, trackStops, travelTime);
        }

        System.out.println("DANE:");

        System.out.println("Ilość dni symulacji: " + simulationDays);
        System.out.println("Limit pasażerów na przystanku: " + stopLimit);
        System.out.println("Liczba przystanków: " + numberOfStops);
        System.out.println("Nazwy przystanków: ");
        for(int i = 0; i < numberOfStops; i++){

            System.out.println("    " + stopsNames[i]);
        }

        System.out.println("Liczba pasażerów: " + numberOfPassengers);
        System.out.println("Limit pasażerów w tramwaju: " + tramLimit);
        System.out.println("Liczba linii tramwajowych: " + getTracks);
        for(int i = 0; i < getTracks; i++){
            System.out.println("Linia nr: " + i);
            System.out.println("    Liczba przystanków: " + saveTrackLengths[i]);
            System.out.println("    Liczba tramwajów: " + saveTrackTrams[i]);
            System.out.print("    ");
            for(int j = 0; j < saveTrackLengths[i]; j++){
                System.out.print(saveLines[i].getStringIndex(j));
                if(j < saveTrackLengths[i] - 1)
                    System.out.print(" -> " + saveLines[i].getIntIndex(j) + "min -> ");
            }
            System.out.println();
            System.out.println("    Czas na pętli: " + saveLines[i].getIntIndex(saveTrackLengths[i] - 1) + "min");
        }


    }

    private void setNumberOfDays(int getNumberOfDays){
        numberOfDays = getNumberOfDays;
    }
    public void setNumberOfTracks(int getNumberOfTracks){
        numberOfTracks = getNumberOfTracks;
    }
    public void setStops(int getPassengerLimit, int getNumberOfStops, String [] getStopsNames){
        numberOfStops = getNumberOfStops;
        TramStop [] tmpTramStopsArray = new TramStop[getNumberOfStops];
        for(int i = 0; i < getNumberOfStops; i++){
            tmpTramStopsArray[i] = new TramStop(getStopsNames[i]);
        }
        allTramStops.initialize(getPassengerLimit, tmpTramStopsArray);
    }
    public void setPassengers(int getNumberOfPassengers){
        numberOfPassengers = getNumberOfPassengers;
        Passenger [] tmpPassengerArray = new Passenger[getNumberOfPassengers];
        for(int i = 0; i < getNumberOfPassengers; i++){
            tmpPassengerArray[i] = new Passenger(allTramStops, allPassengers);
        }
        allPassengers.setAllPassengers(tmpPassengerArray);
    }

    private void addLineToSimulation(int getNumberOfTrams, String [] getNamesOfTrackStops, int [] getTimeLimits){
        TramStop[] tmpTramStopsArray = new TramStop[getNamesOfTrackStops.length];
        Tram[] tmpTramsArray = new Tram[getNumberOfTrams];
        for(int i = 0; i < getNamesOfTrackStops.length; i++){
            tmpTramStopsArray[i] = allTramStops.getStopByName(getNamesOfTrackStops[i], allTramStops);
        }

        for(int i = 0; i < getNumberOfTrams; i++){
            if(i < getNumberOfTrams / 2 + getNumberOfTrams % 2)
                tmpTramsArray[i] = new Tram(null, 0, allTrams);
            else
                tmpTramsArray[i] = new Tram(null, getNamesOfTrackStops.length - 1, allTrams);
        }
        TrackLine tmpTrack = new TrackLine(getTimeLimits, tmpTramStopsArray, tmpTramsArray, allTrackLines.getNextTrackNumber());
        allTrackLines.increaseNextTrackNumber();
        for(int i = 0; i < getNumberOfTrams; i++){
            tmpTramsArray[i].setTrackLine(tmpTrack);
        }
        allTrackLines.addNewTrack(tmpTrack);
    }
    //The following methods reset the trams to their places each day,
    //clear the stops, and set the times for passenger arrivals.
    private void setOneTrackLineOnStart(TrackLine trackLine){
        TramStop firstStop = trackLine.getStopByIndex(0);
        TramStop lastStop = trackLine.getStopByIndex(trackLine.getNumberOfStops() - 1);
        int half = trackLine.getNumberOfTrams() / 2 + trackLine.getNumberOfTrams() % 2;
        for(int i = 0; i < half; i++){
            trackLine.getTramByIndex(i).nextDayTram();
            Event newEvent = new Event(
                    EventTypes.TRAM_ARRIVING,
                    trackLine.getTramByIndex(i),
                    null,
                    firstStop,
                    dayNumber,
                    6 * 60 + i * trackLine.getTimeTillNextTram(),
                    timeIndexes
            );
            eventQueue.addNewEvent(newEvent);
        }
        for(int i = half; i < trackLine.getNumberOfTrams(); i++){
            trackLine.getTramByIndex(i).nextDayTram();
            Event newEvent = new Event(
                    EventTypes.TRAM_ARRIVING,
                    trackLine.getTramByIndex(i),
                    null,
                    lastStop,
                    dayNumber,
                    6 * 60 + (i - half) * trackLine.getTimeTillNextTram(),
                    timeIndexes
            );
            eventQueue.addNewEvent(newEvent);
        }
    }
    private void setOnePassenger(Passenger passenger){
        Event newEvent = new Event(
                EventTypes.PERSON_ARRIVING,
                null,
                passenger,
                passenger.getNearestStop(),
                dayNumber,
                passenger.getArrivalHour() * 60,
                timeIndexes
        );
        eventQueue.addNewEvent(newEvent);
    }

    private void setOneStop(TramStop stop){
        stop.nextDayStop();
    }
    private void OneDaySimulation(){
        timeIndexes = new TimeIndexes();
        for(int i = 0; i < numberOfPassengers; i++){
            Passenger addPassengerEvent = allPassengers.getPassengerByIndex(i);
            addPassengerEvent.nextDayPassenger();
            setOnePassenger(addPassengerEvent);
        }
        for(int i = 0; i < numberOfTracks; i++){
            setOneTrackLineOnStart(allTrackLines.getTrackLineByIndex(i));
        }
        for(int i = 0; i < numberOfStops; i++){
            setOneStop(allTramStops.getStopNameByIndex(i));
        }

        while(!eventQueue.isEmpty()){
            Event onGoingEvent =  eventQueue.poll();
            onGoingEvent.solveEvent(eventQueue, simulationStatistics, allTrams, allTramStops);
        }
        simulationStatistics.nextDay();
    }
    public void playSimulation(){
        System.out.println();
        System.out.println("ZDARZENIA: ");
        for(int i = 0; i < numberOfDays; i++){
            this.OneDaySimulation();
            dayNumber++;
        }
        System.out.println();
        System.out.println("STATYSTYKI:");
        for(int i = 0; i < numberOfDays; i++){
            System.out.println("Statystyki Dnia: " + (i + 1));
            simulationStatistics.displayStatisticsForOneDay(i);
        }
        System.out.println("Statystyki Całej Symulacji:");
        simulationStatistics.displayStatistics();
    }
}
