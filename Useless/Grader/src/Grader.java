import java.util.Scanner;

public class Grader {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is the number grade: ");
        int grade = input.nextInt();
        if (grade < 80) {
            System.out.printf("%d is a C\n", grade );
        }
        else if (grade > 89) {
            System.out.printf("%d is a A\n", grade );
        }
        else {
            System.out.printf("%d is a B\n", grade );
        }
        
        input.close();
    }

}
