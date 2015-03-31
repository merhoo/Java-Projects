
public class TemperatureConversion {
    public static double farenheitToCelsius(double F){
        double celsius = 5.0 * (F - 32) /9.0;
        return celsius;
    }
    public static double celsiusToFarhenheit(double C){
        double farhenheit = C * 9/5 + 32;
        return farhenheit;
    }

}
