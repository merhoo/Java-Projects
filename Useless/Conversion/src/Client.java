import java.util.InputMismatchException;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter degrees in Fahrenheit: ");
        try {
            double fahrenheit = input.nextDouble();
            double celsius = TemperatureConversion.farenheitToCelsius(fahrenheit);
            System.out.printf("%.2f F = %.2f C\n", fahrenheit, celsius);
        } catch (InputMismatchException ime) {
            System.out.println("Error: Invalid input received.");
            input.nextLine();
        }
        System.out.print("Enter degrees in Celsius: ");
        try {
            double Celsius = input.nextDouble();
            double Fahrenheit = TemperatureConversion.celsiusToFarhenheit(Celsius);
            System.out.printf("%.2f C = %.2f F\n", Celsius, Fahrenheit);
        } catch (InputMismatchException ime) {
            System.out.println("Error: Invalid input received.");
            input.nextLine();
        }
        System.out.print("Enter number of days: ");
        try {
            double Days = input.nextDouble();
            int Seconds = TimeConversion.daysToSeconds(Days);
            System.out.printf("%.2f days = %d seconds\n", Days, Seconds);
        } catch (InputMismatchException ime) {
            System.out.println("Error: Invalid input received.");
            input.nextLine();
        }
        System.out.print("Enter number of seconds: ");
        try {
            int seconds = input.nextInt();
            double days = TimeConversion.secondsToDays(seconds);
            System.out.printf("%d seconds = %.2f days\n", seconds, days);
        } catch (InputMismatchException ime) {
            System.out.println("Error: Invalid input received.");
            input.nextLine();
        }
        input.close();
        }
    }
