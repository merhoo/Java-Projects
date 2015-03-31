
public class ArrayListBasics {
    public static int maxValue(int[][] array) {
        int maxval = 0;
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                if (array[row][col] > maxval) {
                    maxval = array[row][col];
                }
            }
        }
        return maxval;
    }
    public static int minValue(int[][] array) {
        int minval = 0;
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                if (array[row][col] < minval) {
                    minval = array[row][col];
                }
            }
        }
        return minval;
    }
    public static int sumMajorDiagonal(int[][] array) throws IllegalArgumentException{
        int sum = 0;
        for (int row = 0; row < array.length; row++) {
            if (array[row].length != array.length) {
                throw new IllegalArgumentException("Error: 2D array is not square.");
            } else {
                for (int col = 0; col < array[row].length; col++) {
                    if (row == col) {
                        sum += array[row][col];
                    }
                }
            }
        } return sum;
    }
    public static int sumMinorDiagonal(int[][] array) throws IllegalArgumentException{
        int sum = 0;
        for (int row = 0; row < array.length; row++) {
            if (array[row].length != array.length) {
                throw new IllegalArgumentException("Error: 2D array is not square.");
            } else {
                for (int col = array[row].length - 1; col >= 0; col--) {
                    if (row + col == array.length - 1) {
                        sum += array[row][col];
                    }
                }
            }
        } return sum;
    }
    public static void toString(int[][]array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                System.out.print(array[row][col] + " ");
            } System.out.println();
        } System.out.println();
    }
    public static void main(String[] args) {
        int [][] array = new int[3][4];//all initialized to 0
        array[0][0] = 2;
        array[0][1] = 5;
        array[0][2] = 8;
        array[0][3] = 8;
        array[1][2] = 3;
        array[2][3] = 4;
        
        toString(array);
        int maxValue = maxValue(array);
        int minValue = minValue(array);
        
        System.out.println("The maximum value in the array is " + maxValue);
        System.out.println("The minimum value in the array is " + minValue);
        System.out.println();
        
        int [][] square = new int[3][3];
        square[0][0] = 2;
        square[0][1] = 5;
        square[0][2] = 8;
        square[1][0] = 8;
        square[1][1] = 1;
        square[1][2] = 3;
        square[2][2] = 4;
        toString(square);
        int majorDiagonalSum = sumMajorDiagonal(square);
        int minorDiagonalSum = sumMinorDiagonal(square);
        System.out.println("The sum of the major diagonal is " + majorDiagonalSum);
        System.out.println("The sum of the minor diagonal is " + minorDiagonalSum);
        System.out.println();
        
        //ragged arrays are 2D arrays where the arrays within are different lengths
        int[][] ragarray = {{1, 3, 5, 7},
                {2, 4},
                {6, 8, 10}};
        //how do you change the second array to {4, 6, 8}?
        int[] row = {4, 6, 8};
        ragarray[1] = row;
        //Reference: chapter 7
    }
    
    
}
