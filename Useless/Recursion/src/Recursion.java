
public class Recursion {
    /*linear recursion has one recursive call during each iteration and there is a pending operation after the recursive call
     * 
    */
    public static int factorial(int n) {
        //base case: used to stop the recursion
        if (n == 0) {
            return 1;
        }
        //without a base case, there would be infinite recursion
        //result in StackOverflowException
        
        //factorial(n-1) is the recursive call
        return n*factorial(n-1);
        //pending operation is *
        //frames of a system stack, element of frame
        //pushing: adding frames to stack
        //popping = removing frames from stack
    }
    
    public static int fibonacci(int n) {
        //tree recursion: branches of evaluations, making multiple operations that
        //it is tree if there is a pending operation that is another recursive call
        //O(2^n) because 
        if (n <= 1) {
            return n;
        } 
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static int power(int x, int y) {
        if (y == 0) {
            return 1;
        }
        return x * power(x, y - 1);
    }
    
    public static int powerTail(int x, int y) throws IllegalArgumentException {
        //tail recursion: no pending operations
        if (y <= 0) {
            return powerTailHelper(x, y, 1);
        }
        throw new IllegalArgumentException("Exponent must be non-negitive");
    }
    
    private static int powerTailHelper(int x, int y, int result) {
        if (y == 0) {
            return result;
        }
        return powerTailHelper(x, y - 1, result *x);
        //accumulation takes place as an argument to the next recursive call
        //tail recursion is better because you don't have to pop your stacks and you can call errors
    }
    public String changeXY(String str) {
        if(str.length() == 0) {
            return "";
        }
        char bleh = str.charAt(str.length() - 1);
        if(bleh == 'x') {
            bleh = 'y';
        }
        return str + changeXY(str.substring(0, str.length() - 1));
       }
    public static void main(String[] args) {
        System.out.println(fibonacci(9));
        System.out.println(fibonacci(40));
    }
}
