
public class TimeConversion {
    public static int daysToSeconds(double days){
        int Seconds = (int)(days * 86400);
        return Seconds;
    }
    public static double secondsToDays(int seconds){
        double Days = (double)seconds / 86400;
        return Days;
    }

}
