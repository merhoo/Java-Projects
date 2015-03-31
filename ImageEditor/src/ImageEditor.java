import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ImageEditor {
    private char[][] asciiImage, original;
    private  int numRows, numCols, borderCount, fillCount;
    
    public ImageEditor(String filename) throws FileNotFoundException, IOException, RuntimeException {
        File file = new File(filename);
        if (!(file.exists())) {
            throw new FileNotFoundException("Error: File '" + filename + "' not found.");
        }
        if (file.length() <= 0) {
            throw new RuntimeException("Error: '" + filename + "' is empty.");
        }
        Scanner input = new Scanner(file);
        String ascii = getAsciiImage(input);
        asciiImage = new char[numRows][numCols];
        original = new char[numRows][numCols];
        for (int i = 0, j = 0, k = 0; i < ascii.length(); i++) {
            if (ascii.charAt(i) != '\n') {
                asciiImage[j][k] = ascii.charAt(i);
                original[j][k] = ascii.charAt(i);
                k++;
            }
            if (ascii.charAt(i) == '\n') {
                j++;
                k = 0;
            }
        }
        
        
    }
    
    public int getFillCount() {
        return fillCount;
    }
    public int getBorderCount() {
        return borderCount;
    }
    public void floodFill(int row, int col, char newPixel) throws IllegalArgumentException {
        try {
            if (asciiImage[row][col] != newPixel) {
                fill(row, col, asciiImage[row][col], newPixel);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new IllegalArgumentException("Error: No pixel at (" + row + ", " + col + ").");
        }
        System.out.println("Original image:");
        toString(original);
        System.out.println("After flood fill:");
        toString(asciiImage);
        System.out.print(getFillCount() + " pixels changed.");

    }
    private void fill(int row, int col, char oldPixel, char newPixel) {
        if (row < numRows && row >= 0 && col < numCols && col >= 0) {
            if (asciiImage[row][col] == oldPixel) {
                asciiImage[row][col] = newPixel;
                fillCount++;
                fill(row + 1, col, oldPixel, newPixel);
                fill(row - 1, col, oldPixel, newPixel);
                fill(row, col - 1, oldPixel, newPixel);
                fill(row, col + 1, oldPixel, newPixel);
            }
        }
        
    }
    public void replaceBorder(int row, int col, char newPixel) throws IllegalArgumentException{
        try {
            if (asciiImage[row][col] != newPixel) {
                border(row, col, asciiImage[row][col], newPixel);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new IllegalArgumentException("Error: No pixel at (" + row + ", " + col + ").");
        }
        System.out.println("Original image:");
        toString(original);
        System.out.println("After replacing border:");
        toString(asciiImage);
        System.out.print(getBorderCount() + " pixels changed.");

    }
    private void border(int row, int col, char oldPixel, char newPixel) {
        if (row < numRows && row >= 0 && col < numCols && col >= 0) {
            if (asciiImage[row][col] == oldPixel) {
                asciiImage[row][col] = newPixel;
                borderCount++;
                border(row + 1, col, oldPixel, newPixel);
                border(row - 1, col, oldPixel, newPixel);
                border(row, col - 1, oldPixel, newPixel);
                border(row, col + 1, oldPixel, newPixel);
                border(row + 1, col + 1, oldPixel, newPixel);
                border(row - 1, col + 1, oldPixel, newPixel);
                border(row - 1, col - 1, oldPixel, newPixel);
                border(row + 1, col - 1, oldPixel, newPixel);
            }
        }
    }
    public String getAsciiImage(Scanner input) {
        StringBuilder asciiString = new StringBuilder();
        numCols = 0;
        numRows = 0;
        while (input.hasNext()) {
            String line = input.nextLine();
            asciiString.append(line);
            asciiString.append("\n");
            numRows += 1;
            numCols = line.length();
        }
        input.close();
        return asciiString.toString();
    }
    
    public static void toString(char[][]array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                System.out.print(array[row][col]);
            } System.out.println();
        } System.out.println();
    }
    
    public static void main(String[] args) {
        if (args.length != 5) {
            System.err.println("Usage: java ImageEditor <ASCII image file> <f|b> <row> <column> <new char>");
            System.exit(1);
        }
        
        try {
            Integer.parseInt(args[2]);
        } catch (NumberFormatException nfe){
            System.err.println("Error: Invalid row value '" + args[2] + "'.");
            System.exit(1);
        }
        try {
            Integer.parseInt(args[3]);
        } catch (NumberFormatException nfe){
            System.err.println("Error: Invalid column value '" + args[3] + "'.");
            System.exit(1);
        }
        if (args[4].length() != 1) {
            System.err.println("Error: Invalid new character '" + args[4] + "'.");
            System.exit(1);
        }
        
        ImageEditor imageditor;
        try {
            imageditor = new ImageEditor(args[0]);
            
            if (args[1].charAt(0) != 'f' && args[1].charAt(0) != 'b') {
                    System.err.println("Error: Invalid option '" + args[1] + "'. Expected 'f' or 'b'.");
                    System.exit(1);
            }
            if (args[1].charAt(0) == 'f') {
                try {
                    imageditor.floodFill(Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4].charAt(0));
                } catch (IllegalArgumentException iae) {
                    System.err.print(iae.getMessage());
                    System.exit(1);
                }
            }
            if (args[1].charAt(0) == 'b') {
                try {
                    imageditor.replaceBorder(Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4].charAt(0));
                } catch (IllegalArgumentException iae) {
                    System.err.print(iae.getMessage());
                    System.exit(1);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.print(fnfe.getMessage());
            System.exit(1);
        } catch (IOException ioe) {
            System.err.print("Error: IOException has occured.");
            System.exit(1);
        } catch (RuntimeException re) {
            System.err.print(re.getMessage());
            System.exit(1);
        }
        
        
        
        
    }
}

