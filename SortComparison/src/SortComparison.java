import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class SortComparison {
    /**
     * Returns a random integer between a and b
     * @param a the minimum value given
     * @param b the maximum value given
     * @return a random integer
     */
    public static int getRandomInt(int a, int b) {
        return (a + (int)(Math.random()*(b- a +1)));
    }
    /**
     * Returns a random array of size n, such that,
     * for every element x in the array,
     *      0 < = x < Integer.MAX_VALUE
     *      
     * @param length the length of the array
     * @return an array of random ints
     */
    public static int[] generateRandomArray(int length) {
        int [] list = new int[length];
        int a = 0, b = Integer.MAX_VALUE, randomInt;
        for (int i = 0; i < length - 1; i++) {
            randomInt = getRandomInt(a, b);
            list[i] = randomInt;
        }
        return list;
    }
    public static int[] makeCopyArray(int[] list) {
        int[] copy = new int[list.length];
        for (int i = 0; i <list.length; i++) {
            copy[i] = list[i];
        }
        return copy;
    }
    /**
     * Swaps two elements in an array
     * 
     * @param list the array in which to swap elements
     * @param a the index of the first element
     * @param b the index of the second element
     */
    public static void swap(int[] list, int a, int b) {
        int temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }
    /**
     * Sorts an array of integers using O(n^2) bubble sort.
     * 
     * @param list the array to be sorted
     */
    public static void bubbleSort(int[] list) {
        int n = list.length;
        while (n > 0) {
            int newN = 0;
            for (int i = 1; i < n; i++) {
                if (list[i - 1] > list[i]) {
                    swap(list, i- 1, i);
                    newN = i;
                }
            } 
            n = newN;
        }
    }
    /**
     * Sorts an array of integers using O(n^2) selection sort.
     * 
     * @param list the array to be sorted
     */
    public static void selectionSort(int[] list) {
        int n = list.length, nMinusOne = list.length - 1;
        for( int i = 0; i < nMinusOne; i++) {
            int minIndex = 1, min = list[i];
            for (int j = i+ 1; j < n; j++) {
                int current = list[j];
                if (current < min) {
                    minIndex = j;
                    min = current;
                }
            }
            if (minIndex != i) {
                swap(list, i, minIndex);
            }//makes n((n-1)/2 comparisons, swaps at most n-1 
        }
    }
    /**
     * Sorts an array of integers using O(n^2) insertion sort.
     * 
     * @param list the array to be sorted
     */
    public static void insertionSort(int[] list) {
        int n = list.length;
        for (int i = 1; i< n; i++) {
            int k, current = list[i];
            for (k = i - 1; k >= 0 && list[k] > current ; k--) {
                list[k + 1] = list[k];
            }
            list[k+ 1] = current;
        }
    }
    public static void main(String[] args) {
        System.out.println("--- Sorting Algorithms Comparison ---");
        System.out.println();
        Scanner input = new Scanner(System.in);
        int size = 0;
        boolean loop = true;
        while (loop) {
            System.out.print("Enter array size: ");
            try {
                size = input.nextInt();
            } catch (InputMismatchException ime) {
                System.err.print("Error: array size must be an integer.");
                System.exit(1);
            }
            if (size < 0) {
                System.err.println("Error: array size cannot be less than 0");
                System.exit(1);
            } 
            loop = false;
        }
        int[] listOfRandomInts = generateRandomArray(size);
        
        int[] bubbleArray = makeCopyArray(listOfRandomInts);
        System.out.print("Running bubble sort...");
        long bubbleSortStart = 0;
        bubbleSortStart = System.currentTimeMillis();
        bubbleSort(bubbleArray);
        double bubbleSortElapsed = (System.currentTimeMillis() - bubbleSortStart) / 1000.0;
        System.out.println("done.");
        System.out.printf("Elapsed time: %.3f second.\n", bubbleSortElapsed);
        System.out.println();
        
        int[] selectionArray = makeCopyArray(listOfRandomInts);
        System.out.print("Running selection sort...");
        long selectionSortStart = 0;
        selectionSortStart = System.currentTimeMillis();
        selectionSort(selectionArray);
        double selectionSortElapsed = (System.currentTimeMillis() - selectionSortStart) / 1000.0;
        System.out.println("done.");
        System.out.printf("Elapsed time: %.3f seconds.\n", selectionSortElapsed);
        System.out.println();
        
        int[] insertionArray = makeCopyArray(listOfRandomInts);
        System.out.print("Running insertion sort...");
        long insertionSortStart = 0;
        insertionSortStart = System.currentTimeMillis();
        insertionSort(insertionArray);
        double insertionSortElapsed = (System.currentTimeMillis() - insertionSortStart) / 1000.0;
        System.out.println("done.");
        System.out.printf("Elapsed time: %.3f seconds.\n", insertionSortElapsed);
        System.out.println();
        
        int[] javaArray = makeCopyArray(listOfRandomInts);
        System.out.print("Running Java sort...");
        long javaSortStart = 0;
        javaSortStart = System.currentTimeMillis();
        Arrays.sort(javaArray);
        double javaSortElapsed = (System.currentTimeMillis() - javaSortStart) / 1000.0;
        System.out.println("done.");
        System.out.printf("Elapsed time: %.3f seconds.\n", javaSortElapsed);
        input.close();
    }
}