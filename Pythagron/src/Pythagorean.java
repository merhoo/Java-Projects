import java.util.Scanner;
import java.util.InputMismatchException;


public class Pythagorean {
    public static void Pythagoreus(int n) {
        int counter = 1;
        for (int c = 5, i = 8; c <= n + 1; i+=4) {//must start with what is given
            int b = c - 1;//b is always one less than c ex.3 4 5
            for (int a = 3; a*a + b*b <= c*c; a += 2) {//a is increasing by 2 ex, 3 4 5; 5 12 13, stops when a has reached max value in triple, so c is always less than n
                if (a*a + b*b == c*c) {//Pythagorean Theorem
                    System.out.println(counter + ") " + a + " " + b + " " + c);
                    counter += 1;
                    c += i;// c is increasing by 8+=4
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("--- Pythagorean Triple Generator ---");
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter max value for c: ");
        try {
            int n = input.nextInt();
            long start = System.currentTimeMillis();
            Pythagoreus(n);
            double elapsed = (System.currentTimeMillis() - start) / 1000.0;
            System.out.println("Searching complete...");
            System.out.printf("Elapsed time: %.3f seconds", elapsed);
        } catch (InputMismatchException ime) {
            System.err.println("Error: Input is not an integer.");
            System.exit(1);
        }
        input.close();
        
        
    }

}
