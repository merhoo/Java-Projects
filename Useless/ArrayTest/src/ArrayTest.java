
public class ArrayTest {
    public static int getRandomInt(int a, int b) {
        return (a + (int)(Math.random()*(b- a +1)));
    }
    public static void displayArray(int[] values) {
        System.out.print("[");
        for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                System.out.print(values[i]);
            } else {
                System.out.print(", " + values[i]);
            }
            System.out.println("]");
        }
    }
    public static int getMax(int[] values) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (max <= values[i]) {
                max = values[i];
            }
        }
        return max;
    }
    public static int getMin(int[] values) {
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (min >= values[i]) {
                min = values[i];
            }
        }
        return min;
    }
    public static boolean findFirstIndex(int input, int[] values) {
        for (int value: values) {
            if (value == input) {
                return true;
            }
        } return true;
            
    }
    public static void main(String[] args) {
        int[] values = new int[10];
        for (int i = 0; i < values.length; i++) {
            values[i] = getRandomInt(1,100);
        }
        System.out.println(findFirstIndex(50, values));
        
    }
}
