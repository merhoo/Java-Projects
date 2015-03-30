public class FoorLoops {
    public static void main(String[] args) {
        for (int i = 0, product = 1; i <= 10; i++, product *= 2) {
            System.out.println("2^" + i + " = " + product);
        }
        for (int j = 1; j <= 12; j++) {
            System.out.println();
            for (int k = 1; k <= 12; k++) {
                int l = j * k;
                System.out.println(j + " x " + k + " = " + l);
            }
        }
        for (int i = 1; i <= 10000; i++) {
            int k = 0;
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    k += j;

                }
            }
            if (i == k) {
                System.out.println(i);
            }
        }
    }
}
