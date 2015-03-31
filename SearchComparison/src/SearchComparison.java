import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SearchComparison { 
    public static int getRandomInt(int a, int b) {
        return (a + (int)(Math.random()*(b- a +1)));
    }
    public static int[] generateRandomSortedArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = getRandomInt(1,Integer.MAX_VALUE);
        }
        Arrays.sort(array);
        return array;
    }
    public static int linearSearch(int[] list, int key) {
        for (int i = 0; i < list.length; i++) {
            if (key == list[i]) {
                return i;
            }
        }    
        return -1;
    }
    public static int binarySearch(int[] list, int key) {
        int low = 0, high = list.length - 1;
        while (high >= low) {
            int mid = low + (high - low)/2;
            if ( key == list[mid]) {
                return mid;
            }
            else if (key > list[mid]) {
                low = mid + 1;
            }
            else {
                high = mid -1;
            }
        }
        return -low -1;
        
    }
        
    public static void main(String[] args) {
        System.out.println("--- Linear Binary/Search Comparison ---");
        System.out.println();
        int listsize = 0;
        int numofkeys = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter list size: ");
        try {
            listsize = input.nextInt();
            System.out.print("Enter number of keys: ");
        } catch (InputMismatchException ime) {
            System.err.println("Error: Invalid input received.");
            input.nextLine();
            System.exit(1);
        }
        if (listsize < 0) {
            System.err.println("Error: List size cannot be negative");
            System.exit(1);
        }
        try {
            numofkeys = input.nextInt();
        } catch (InputMismatchException ime) {
            System.err.println("Error: Invalid input received.");
            input.nextLine();
            System.exit(1);
        }
        if (numofkeys < 0) {
            System.err.println("Error: Number of keys cannot be negative");
            System.exit(1);
        }
        
        int[] list = generateRandomSortedArray(listsize);
        int[] keys = generateRandomSortedArray(numofkeys);
                
        System.out.print("Running linear search...");
        long linstart = 0;
        linstart = System.currentTimeMillis();
        for (int i = 0; i < numofkeys; i++) {
            linearSearch(list, keys[i]);
        }
        System.out.println("done.");
        double linelapsed = (System.currentTimeMillis() - linstart) / 1000.0;
        System.out.printf("Elapsed time: %.3f seconds\n", linelapsed);
        
        System.out.print("Running binary search...");
        long binstart = 0;
        binstart = System.currentTimeMillis();
        for (int i = 0; i < numofkeys; i++) {
            binarySearch(list, keys[i]);
        }
        System.out.println("done.");
        double binelapsed = (System.currentTimeMillis() - binstart) / 1000.0;
        System.out.println("Binary searching complete...");
        System.out.printf("Elapsed time: %.3f seconds", binelapsed);
        System.exit(1);
        input.close();
    }
}
