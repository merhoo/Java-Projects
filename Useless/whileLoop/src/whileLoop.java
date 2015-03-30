
public class whileLoop {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 10) {
            System.out.print(i++ + ", ");
        
        }
        System.out.println();
        power(); //Allows you to see the output of the power function
    }
    
    public static void power() {
        int i = 0, product = 1;
        while (i <= 10) {
            System.out.println("2^" + i + " = " + product);
            product <<= 1; //This works because of binary numbers. Ask Dr. B if you need help.
            i++;
        }
    }

}
//Some statement in body of loop must alter state of variables so that eventually the condition of a while loop becomes false (ending the loop)
//an infinite loop is when a loop does not terminate (the condition never becomes false)