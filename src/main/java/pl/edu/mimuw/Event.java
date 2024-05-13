package pl.edu.mimuw;

//This class handles all events that may appear on the timeline.
//A comment is added to the things that are not obvious.

public class Event implements Comparable<Event>{

    //The following variables store information related to the event.
    //There is no need to store the trackLine in this version, as it is
    //available from the eventTram variable. The variables that are not needed
    //for a particular event are simply set to null.

    private final EventTypes eventType;
    private final Tram eventTram;
    private final Passenger eventPassenger;
    private final TramStop eventStop;

    private final TimeIndexes timeIndexes;
    private final int eventDay;
    private final int minutes;
    private final long timeIndex;

    public Event(EventTypes getType, Tram getTram, Passenger getPassenger, TramStop getStop, int getDay, int getMinutes, TimeIndexes getTimeIndexes){
        eventType = getType;
        eventTram = getTram;
        eventPassenger = getPassenger;
        eventStop = getStop;
        eventDay = getDay;
        minutes = getMinutes;
        timeIndexes = getTimeIndexes;
        timeIndex = timeIndexes.getTimeIndex(minutes);
    }

    public int getMinutes() {
        return minutes;
    }
    public long getTimeIndex(){
        return timeIndex;
    }

    /*
    One might justifiably question why the passenger has three methods related to
    disembarking that add new events with the same time. Let’s envision it this way:
    a person attempting to exit, in an infinitesimally small fraction of a second
    (assuming the brain’s neurons operate with a delta t = 0), upon observing
    the tram’s arrival at the stop, determines whether they can disembark
    successfully or not. Consequently, the event of success or failure is added to the timeline.

    Real Reason: without this mechanism, a passenger would exit the tram before it arrived.
    Because The event related to a passenger's departure is always added to the timeline before the tram's event.
    This results from the fact that when a passenger boards, they already know they want to get off at,
    for example, 4 stops later, but the tram only adds an event about the next stop (resulting in a more optimal solution).
    Another solution would involve placing all tram arrivals on the timeline at the very beginning,
    but that would be a temporal absurdity.
     */
    public void solveEvent(EventQueue queue, Statistics statistics, AllTrams allTrams, AllTramStops allTramStops){
        if(eventType == EventTypes.PERSON_ARRIVING){
            solvePersonArriving(allTramStops);
        }
        if(eventType == EventTypes.EXIT){
            solvePassengerExit(queue, allTramStops);
        }
        if(eventType == EventTypes.EXIT_SUCCESS){
            solvePassengerExitSuccess();
        }
        if(eventType == EventTypes.TRAM_ARRIVING){
            solveTramArriving(queue);
        }
        if(eventType == EventTypes.TRAM_CLOSING){
            solveTramClosing(queue);
        }
        if(eventType == EventTypes.ENTRANCE){
            solvePassengerEntrance(queue, statistics, allTrams);
        }
        /*
        This is added as a comment because, in my opinion, the information
        that the passenger failed to get off is not very important, but for
        the person using the simulation, it might be, so I add this possibility.
        if(eventType == EventTypes.EXIT_FAIL){
            solvePassengerExitFailed();
        }
        */
    }

    private void solvePersonArriving(AllTramStops allTramStops){
        if(allTramStops.getPassengerLimit() == eventStop.getPeopleAtStop()){
            eventType.printArrivingFailedInfo(
                    eventDay,
                    minutes / 60,
                    minutes % 60,
                    eventPassenger.getPassengerNumber(),
                    eventStop.getName()
                    );
            return;
        }
        eventType.printArrivingSuccessInfo(
                eventDay,
                minutes / 60,
                minutes % 60,
                eventPassenger.getPassengerNumber(),
                eventStop.getName()
        );
        eventStop.addPerson(eventPassenger);
    }

    private void solveTramArriving(EventQueue queue){
        eventType.printTramArrivingInfo(
                eventDay,
                minutes / 60,
                minutes % 60,
                eventTram.getLine().getTrackNumber(),
                eventTram.getSideNumber(),
                eventStop.getName()
        );

        if(eventTram.getNextStop() != eventTram.getThisStop()) {
            Event newEvent2 = new Event(
                    EventTypes.ENTRANCE,
                    eventTram,
                    null,
                    eventStop,
                    eventDay,
                    minutes,
                    timeIndexes
            );
            queue.addNewEvent(newEvent2);
        }
        Event newEvent1 = new Event(
                EventTypes.TRAM_CLOSING,
                eventTram,
                null,
                eventStop,
                eventDay,
                minutes,
                timeIndexes
        );
        queue.addNewEvent(newEvent1);

    }
    //This method checks if a given passenger can get off the tram. If it's possible,
    //a success event is added; however, if not, we check if the tram will still pass
    //through the target stop. If so, an associated event is added.
    private void solvePassengerExit(EventQueue queue, AllTramStops allTramStops){
        if(eventStop.getPeopleAtStop() < allTramStops.getPassengerLimit()){
            eventStop.addPerson(eventPassenger);
            Event newEvent = new Event(
                    EventTypes.EXIT_SUCCESS,
                    eventTram,
                    eventPassenger,
                    eventStop,
                    eventDay,
                    minutes,
                    timeIndexes
            );
            queue.addNewEvent(newEvent);
        }
        else{
            int newArrivingTime;
            TrackLine eventTrack = eventTram.getLine();
            if(eventTram.getThisStop() == eventTram.getNextStop()){
                if(eventTram.getFirstStop() == eventTram.getNextStop() && minutes + eventTrack.getLoopTime() > 23 * 60){
                    return;
                }
                newArrivingTime = minutes + eventTrack.getLoopTime();
            }
            else if(Math.abs(eventTram.getNextStop() - eventTram.getFirstStop()) > Math.abs(eventTram.getThisStop() - eventTram.getFirstStop())){
                newArrivingTime = minutes + eventTrack.getTimeSum() - 2 * eventTrack.calculateTimeDistance(eventTram.getThisStop(), eventTram.getFirstStop()) - eventTrack.getLoopTime();
            }
            else{
                int distanceTillRepeat = eventTrack.calculateTimeDistance(eventTram.getThisStop(), eventTram.getFirstStop());
                distanceTillRepeat += eventTrack.getLoopTime();
                if(minutes + distanceTillRepeat > 23 * 60)
                    return;
                newArrivingTime = minutes + eventTrack.calculateTimeDistance(eventTram.getThisStop(), eventTram.getFirstStop()) * 2 + eventTrack.getLoopTime();
            }
            Event newEvent = new Event(
                    EventTypes.EXIT,
                    eventTram,
                    eventPassenger,
                    eventStop,
                    eventDay,
                    newArrivingTime,
                    timeIndexes
            );
            queue.addNewEvent(newEvent);

            /* Related to the above
            Event newEvent0 = new Event(
                    EventTypes.EXIT_FAIL,
                    eventTram,
                    eventPassenger,
                    eventStop,
                    eventDay,
                    minutes,
                    timeIndexes
            );
            queue.addNewEvent(newEvent0);
            */

        }
    }
    private void solvePassengerExitSuccess(){
        eventPassenger.setStopArrival(minutes);
        eventType.printExitSuccessInfo(
                eventDay,
                minutes / 60,
                minutes % 60,
                eventPassenger.getPassengerNumber(),
                eventTram.getLine().getTrackNumber(),
                eventTram.getSideNumber(),
                eventStop.getName()
        );
        eventTram.personExits();
    }
    /*
    Related to the above
    private void solvePassengerExitFailed(){
        eventType.printExitFailedInfo(
                eventDay,
                minutes / 60,
                minutes % 60,
                eventPassenger.getPassengerNumber(),
                eventTram.getLine().getTrackNumber(),
                eventTram.getSideNumber(),
                eventStop.getName()
        );
    }
    */

    //The event related to a passenger boarding the tram is handled in the following way:
    //People at the stop form a queue based on a first-come, first-served basis, which can be
    //an unpleasant reality for those in a hurry. As long as the queue at the stop is not empty
    //and there is space in the tram, the next people enter (unless it is the last stop),
    //choose any remaining stop on the route they wish to reach, and an associated event is created.
    //Additionally, since each passenger keeps information about when they arrived at the stop
    //(not necessarily their first), statistics include information about how long they waited.
    private void solvePassengerEntrance(EventQueue queue, Statistics statistics, AllTrams allTrams){
        TrackLine eventTrack = eventTram.getLine();
        while(eventTram.getPeopleInsideTram() < allTrams.getTramsLimit() && eventStop.getPeopleAtStop() > 0){
            Passenger passengerEnters = eventStop.getFirstPassenger();
            int DestinationStopIndex;
            if(eventTram.getNextStop() > eventTram.getThisStop()){
                DestinationStopIndex = Losowanie.losuj(eventTram.getNextStop(), eventTrack.getNumberOfStops() - 1);
            }
            else{
                DestinationStopIndex = Losowanie.losuj(0, eventTram.getNextStop());
            }
            TramStop DestinationStop = eventTrack.getStopByIndex(DestinationStopIndex);
            int timeArrival = minutes + eventTrack.calculateTimeDistance(eventTram.getThisStop(), DestinationStopIndex);
            Event newEvent = new Event(
                    EventTypes.EXIT,
                    eventTram,
                    passengerEnters,
                    DestinationStop,
                    eventDay,
                    timeArrival,
                    timeIndexes
            );
            queue.addNewEvent(newEvent);
            statistics.addTimeWaiting( minutes - passengerEnters.getStopArrival() );

            eventTram.personEnters();
            eventType.printEntranceSuccessInfo(
                    eventDay,
                    minutes / 60,
                    minutes % 60,
                    passengerEnters.getPassengerNumber(),
                    eventTrack.getTrackNumber(),
                    eventTram.getSideNumber(),
                    DestinationStop.getName()
                    );

        }
    }

    //This event is related to closing the tram doors and inserting an event about
    //when it will arrive at the next stop.
    private void solveTramClosing(EventQueue queue){
        TrackLine eventTrack = eventTram.getLine();
        //End of workday, newEvent is not added.
        if(eventTram.getThisStop() == eventTram.getNextStop() && eventTram.getFirstStop() == eventTram.getThisStop() && minutes + eventTrack.getLoopTime() > 23 * 60){
            eventType.printEndOfWorkDay(
                    eventDay,
                    minutes / 60,
                    minutes % 60,
                    eventTrack.getTrackNumber(),
                    eventTram.getSideNumber()
            );
            return;
        }
        int timeArrival;
        if(eventTram.getThisStop() == eventTram.getNextStop()){
            eventType.printTramLoopInfo(
                    eventDay,
                    minutes / 60,
                    minutes % 60,
                    eventTrack.getTrackNumber(),
                    eventTram.getSideNumber(),
                    eventTrack.getLoopTime()
            );
            timeArrival = minutes + eventTrack.getLoopTime();
        }
        else{
            timeArrival = minutes + eventTrack.calculateTimeDistance(eventTram.getThisStop(), eventTram.getNextStop());
        }
        Event newEvent = new Event(
                EventTypes.TRAM_ARRIVING,
                eventTram,
                null,
                eventTrack.getStopByIndex(eventTram.getNextStop()),
                eventDay,
                timeArrival,
                timeIndexes
        );
        eventTram.moveToNextStation();
        queue.addNewEvent(newEvent);
    }

    //Method to compare two Events
    @Override
    public int compareTo(Event other){
        if(minutes == other.getMinutes()){
            return Long.compare(timeIndex, other.getTimeIndex());
        }
        return Integer.compare(minutes, other.getMinutes());
    }
}

