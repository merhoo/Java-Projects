
public class Quiz1 {
    public static int[] reverse(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0, j = a.length - i; i < a.length - 1; i++, j--) {
            b[i] = a[j];
        }
        return b;
    }
    /*public static int[] oddnums(int[] a) {
        String odds = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                odds += a[i];
            }
        }
        int[] b = new int[odds.length()];
        for (int i = 0; i <= odds.length(); i++) {
            b[i] = odds[i];
        }
        
    }
    */
    public static int[] fibonnacinums(int n) {
        int[] p = new int[n];
        int a = 0, b = 1, c = 0;
        if (n > 0) {
            p[0] = a;
        }
        if (n > 1) {
            p[1] = b;
        }
        if (n > 2) {
            for (int i = 2; i <= p.length; i++) {
                c = a + b;
                b = c;
                a = b;
                p[i] = c;
            }
        } 
        return p;
    }
    public static void main(String[] args) {
        //int[] a = {1, 2, 3, 4, 5, 6, 8, 7};
        
        /*int[] oddones = oddnums(a);
        int[] fibnums = new int[a.length];
        for (int i = 0; i < oddones.length - 1; i++) {
            System.out.print(oddones[i] + " ");
        }
        */
    }
        
}