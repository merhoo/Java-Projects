
public class Sorting {
    public static void swap(int[] list, int a, int b) {
        int temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }
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
    public static void bubbleSort2(int[] list) {
        int n = list.length, p = 1;
        boolean x = false;
        while (!x && p < n) {
            x = true;
            for (int i = 0; i < n; i++) {
                swap(list, i , i+ 1);
                x = false;
            }
            p += 1;
        }
        
    }
    public static void selectionSort(int[] list) {// made static so you could sort from anywhere
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
    public static void mergeSort(int[] list) {
        mergesort(list, new int[list.length], 0, list.length - 1);
    }
    private static void mergesort(int[] list, int[] scratch, int low, int high) {
        if (low < high) {
            int mid = low + ((high + low) >> 1);
            mergesort(list, scratch, low, mid);
            mergesort(list, scratch, mid+ 1, high);
            int lowIndex = low, highIndex = mid+1;
            
            for (int k = low; k <= high; k++) {
                if (lowIndex <= mid && highIndex > high || list[lowIndex] < list[highIndex]) {
                    scratch[k] = list[lowIndex++];
                }
            }
            
        }
        for (int k = low; k <= high; k++) {
            list[k] = scratch[k];
        }
    }
}