
public class TestOct {
     public static void main(String[] args) {
        String s, t;
        s = "Jeremy";
        t = "Paras";
        if (s.equals(t))//only one line, don't need curly braces
            System.out.println("Same");
        else
            System.out.println("Different");    
        int i = 2, j = 3, k = 4;
        if (i < j)
        if (j < k)
        System.out.println("transitive");
        else//closest to last loop
        System.out.println("No.");
        else
        System.out.println("yes.");
        
        String a, b;//local variables must be initialized before use in methods
        a = "John";
        b = "Smith";
        String c = a + " "+ b;
        System.out.print(c);
        //java byte code, java virtual machine, it runs through that, not directly on machine
        //&& is and
        //|| is or
        //IDE stands for integrated development environment aka eclipse
        //binary operator
        //look at quiz
        Novtest();
    }
    public static boolean isGood(int num) {
        if (num >= 100) {
            return true;
        }
        return false;
    }
    public static void Novtest() {
        int num = 3;
        while (num >= -1) {
            num -=2;
        }
        //3 boxes of loops, which one prints out the same output//nested loops, sentinel, brute force
        int count = 0, sum = 0;
        while (count < 3) {
            sum += count++;
            System.out.println(count + " " + sum);
        }
        for (int i = 1; i <=3; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
    }
}    
