package pl.edu.mimuw;

public enum EventTypes {
    EXIT,
    EXIT_SUCCESS,
    EXIT_FAIL,
    ENTRANCE,
    PERSON_ARRIVING,
    WAITING,
    TRAM_ARRIVING,
    TRAM_LOOP,
    TRAM_CLOSING;


    public void printExitSuccessInfo(int day, int hour, int minutes, int passengerNumber, int trackLine, int sideNumber, String stopName){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ":  Pasażer " + passengerNumber +
                " wysiadł z tramwaju linii " + trackLine + " (nr. boczny " + sideNumber + ")" + "na przystanku " + stopName);
    }

    public void printExitFailedInfo(int day, int hour, int minutes, int passengerNumber, int trackLine, int sideNumber, String stopName){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ":  Pasażer " + passengerNumber +
                " Próbował wysiąść z tramwaju linii " + trackLine + " (nr. boczny " + sideNumber + ")" + "na przystanku " +
                stopName + " ale przystanek był pełny");
    }
    public void printEntranceSuccessInfo(int day, int hour, int minutes, int passengerNumber, int trackLine, int sideNumber, String stopName){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ":  Pasażer " + passengerNumber +
                " wsiadł do tramwaju linii " + trackLine + " (nr. boczny " + sideNumber + ")" + " Z zamiarem dojechania do przystanku " + stopName);
    }
    public void printEntranceFailedInfo(int day, int hour, int minutes, int passengerNumber, int trackLine, int sideNumber){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ":  Pasażer " + passengerNumber +
                " Próbował wsiąść do tramwaju linii " + trackLine + " (nr. boczny " + sideNumber + ")" + "Lecz okazał się pełny ");
    }
    public void printArrivingSuccessInfo(int day, int hour, int minutes, int passengerNumber, String stopName){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ":  Pasażer " + passengerNumber +
                " dotarł na przystanek " + stopName + " i czeka na tramwaj");
    }
    public void printArrivingFailedInfo(int day, int hour, int minutes, int passengerNumber, String stopName){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ": Pasażer " + passengerNumber +
                " próbował stanąć na przystanku " + stopName + " lecz okazał się pełny.");
    }
    public void printTramArrivingInfo(int day, int hour, int minutes, int trackLine, int sideNumber, String stopName){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ": Tramwaj linii " + trackLine +
                " (nr. boczny " + sideNumber + ")" + " wjeżdża na przystanek " + stopName);
    }
    public void printTramLoopInfo(int day, int hour, int minutes, int trackLine, int sideNumber, int loopTime){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ": Tramwaj linii " + trackLine +
                " (nr. boczny " + sideNumber + ")" + " wjeżdża na pętle i będzie czekał " + loopTime + " minut.");
    }

    public void printEndOfWorkDay(int day, int hour, int minutes, int trackLine, int sideNumber){
        System.out.println("Dzień: " + day + " Czas: " + TimeFormatter.displayTime(hour) + ":" + TimeFormatter.displayTime(minutes) + ": maszynista tramwaju linii " + trackLine +
                " (nr. boczny " + sideNumber + ")" + " kończy pracę i idzie na zasłużony odpoczynek");
    }

}
