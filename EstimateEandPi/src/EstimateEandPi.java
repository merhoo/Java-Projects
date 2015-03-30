import java.util.Scanner;
import java.lang.Integer;

public class EstimateEandPi {
    public static double e(int iteration) {
        double e = 1;
        long n = 1;
        for (double i = 1; i <= iteration; i++) {
            if (e < 0) {
                break;
            }
            else {
               e += 1/(i*n);
             n *= i; 
            }         
        }return e;
        
    }
    public static double pi(int iteration) {
        double pi = 0;
        if (iteration == 1) {
            pi = 1.0;
        }
        else {
            for (double lower = 0, n = 0; lower <= iteration; lower++)  {
                if (lower % 2 == 0) {
                    n = 1;
                }
                else {
                    n = -1;
                }
                pi += n/ (2.0 * lower + 1);
            }
        }
        return pi * 4;
    }
    public static void main(String[] args) {
        String iteration = null;
        int n = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");   
        try {
            iteration = input.nextLine();
            n = java.lang.Integer.parseInt(iteration);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input '" + iteration + "' received.");
            System.exit(0);
        }
        double e = e(n);
        System.out.printf("e : %.5f\n", e);
        
        double pi  = pi(n);
        System.out.printf("pi: %.5f\n", pi);
                
    }

}
