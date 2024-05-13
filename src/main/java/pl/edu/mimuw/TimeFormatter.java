package pl.edu.mimuw;

//Class that allows to print the correct hour and minutes
//9:2 X
//09:02 √
public class TimeFormatter {
    public static String displayTime(int time) {
        if (time < 10) {
            return "0" + time;
        }
        return Integer.toString(time);
    }
}
