
public class FourPatterns {
    public static void main(String[] args) {
        System.out.println("Pattern I");
        for (int i = 1; i <= 6; i++) {
            for (int j = 1;j <= i ; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Pattern II");
        for (int i = 1; i <= 6; i++) {
            for (int j = 1, max = 6 - i; j <= max; j++) {
                System.out.print(j + " ");
            }
            System.out.println();            
        }
        System.out.println();
        System.out.println("Pattern III");
        for (int i = 1; i <= 6; i++) {
            for (int j = i + 1; j <= 6; j++) {
                System.out.print("  ");
            }
             for (int j = i; j >= 1; j--) {
                 System.out.print(j + " ");
             }
             System.out.println();
                
        }
        System.out.println();
        System.out.println("Pattern IV");
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j < 1; j++) {
                System.out.print("  ");
            }
        
            
            for (int j = 1; j <= (6 - i + 1); j++) {
                System.out.print("j");
                if (j != (6 - i + 1)) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
        
    }
}
    
    
