import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class SSNValidator {
    private File file;
    static Scanner input;
    static PrintWriter outputFile;
    
    public static boolean isSSNValid(String ssn) {
        String[] num = null;
        int lineNumber = 0;
        StringBuilder endStr = new StringBuilder();
        while (input.hasNext()) {
            String[] numbers = ssn.split("-");
            lineNumber++;
            if (lineNumber > 1) {
                endStr.append("\n");
            }
            for (int i = 0; i < 3; i++) { 
                if (num[i].length() != 3 && i < 2) {
                    return false;
                } else if (num[i].length() != 4 && i == 2) {
                    return false;
                }
            } 
        } return true;
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java FileTesting <input file>");
            System.exit(1);
        } 
        StringBuilder endStr = new StringBuilder();
        //SSNValidator FT = null;
        
        try {
            input = new Scanner(new File(args[0]));
            outputFile = new PrintWriter(new BufferedWriter(new FileWriter(args[1]))); 
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error: File '" + args[0] + "' not found.");
            System.exit(1);
        }  catch (IOException ioe) {
            System.err.println("Error: Cannot open file '" + args[1] + "' for output.");
            System.exit(1);
        }
        try {
            outputFile.write(num);
            outputFile.close();
        } catch (IOException ioe) {
            System.err.println("Error: Cannot open file '" + args[1] + "' for output.");
            System.exit(1);
        }
        input.close();
    }

}
