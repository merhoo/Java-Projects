
public class first {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {//for (initialization; loop termination; update) {
            System.out.print(i + ", ");
        }
        System.out.println();
        int j = 1;
        for ( ; j <= 10; j++) {
            System.out.print(j + ", ");
        }
        int k = 1;
        for ( ; k <= 10; ) {
            System.out.print(k++);          
        }
        for ( ; ; ) {
            System.out.print(k++);
            if (k == 11) {
                break;
            }
        }
        for ( ; ; ) ;//valid, equivalent to pass
        
    }
}
