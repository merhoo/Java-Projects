import java.util.InputMismatchException;
import java.util.Scanner;

public class numberGuess {
    public static final int max_tries = 7;
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        System.out.println("--- Welcome to 'Guess My Number' ---");
        System.out.println();
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Try to guess the number in 7 attempts...");
        int counter = 1;
        int random = getRandomInt(1,100);
        Scanner input = new Scanner(System.in);
        System.out.print("Enter guess " + counter + ": ");
        int guess = input.nextInt();
        try { 
            
        }
        catch (InputMismatchException ime) {
            System.out.println("Error: Invalid input received.");
            input.nextLine();
        }
        while (counter <= max_tries) {
            if (guess == random) {
                if (counter == 1) {
                    System.out.println();
                    System.out.println("Congradulations! You Correctly guessed the number " + random + ", and it took you only " + counter + "try!");
                    break;
                }
                else {
                    System.out.println();
                    System.out.println("Congradulations! You Correctly guessed the number " + random + ", and it took you only " + counter + "tries!");
                }
            }
            else if (guess > random) {
                System.out.println("   Lower...");
            }
            else {
                System.out.println("   Higher...");
            }
            if (counter == max_tries) {
                if (max_tries == 1) {
                    System.out.println();
                    System.out.println("Sorry, you did not guess the number " + random + ", in " + max_tries + "tries.");
                }
                
            }
            ++counter;
            
        }
        
        
    }
    public static int getRandomInt(int a, int b) {
        return (a + (int)(Math.random()*(b- a +1)));
    }

}
