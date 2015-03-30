
import java.util.Random;  
import javax.swing.*;  
import java.util.Scanner;  
  
public class guesss{  
  
    public static void main(String[] args){  
  
        Scanner scan = new Scanner(System.in);  
  
        int intTarget;  
        int intGuess;  
        int intTries=0;  
        int intChoice=1; //Initiate variables for program  
  
  
Random randomNumber = new Random();  
intTarget = randomNumber.nextInt(10) + 1;  
System.out.println(intTarget);  
  
do { ???  
  
    do {  
        System.out.println("Please enter a guess: ");  
    intGuess = scan.nextInt();  
    System.out.println("Your guess is  " + intGuess);  
        if(intGuess < intTarget)  
        {System.out.println("Your guess is too low!");  
        intTries++;  
        }  
        else if(intGuess > intTarget)  
        {System.out.println("Your guess is too high!");  
        intTries++;  
        }  
        else  
        {System.out.println("You guessed correct!");  
        intTries++;  
        }  
    }while (intGuess != intTarget);  
    System.out.println("You have guess correctly! It took you " + intTries + " tries to guess the number!");  
    System.out.println();  
    intChoice = scan.nextInt();  
  
}while (intChoice != 1);  
System.out.println("Thanks for playing");  
    }  
}  