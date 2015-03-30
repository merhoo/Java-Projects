import java.util.Scanner;

public class CoinFlip {
    public static double getRandomNum(double a, double b) {
        return (a + (double)(Math.random()*(b- a +1)));
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("Let's flip a coin...");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter heads or tails: ");
        String coin = input.nextLine().trim();
        input.close();
        
        boolean comparison1 = coin.equalsIgnoreCase("heads");
        boolean comparison2 = coin.equalsIgnoreCase("tails");
        if (comparison1 == false) {
            if (comparison2 == false) {
                System.err.println("Error: You must enter heads or tails. Please rerun the program.");
                System.exit(1);
            }
        }
        
        double random = getRandomNum(0,1);
        if (random < 0.5) {
            System.out.println("Coin flipped: heads");
            if (comparison1 == true) {
                System.out.println("You win!");
            }
            else {
                System.out.println("Sorry, you lose.");
            }
        }
        
        else if (random > 0.5) {
            System.out.println("Coin flipped: tails");
            if (comparison2 == true) {
                System.out.println("You win!");
            }
            else {
                System.out.println("Sorry, you lose.");
            }
        }
            
        
    }
    

}
