import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Binary Decoder object reads a stream of binary data from a file and
 * converts the binary message to ASCII text.
 */
public class BinaryDecoder {
    private final Scanner input;
    private int lineNumber;
    
    /**
     * Constructs a binary decoder that reads a binary stream from 
     * inputFilename, converts the binary to ASCII, and display the decoded
     * ASCII message on the screen
     * 
     * @param inputFilename - the name of the file to read
     * 
     * @throws FileNotFoundException - if the input file does not denote
     * an existing, writable regular file, or if some other error occurs 
     * while opening the file
     */
    public BinaryDecoder(String inputFilename)
            throws FileNotFoundException {
        
        this.input = new Scanner(new File(inputFilename));
        this.lineNumber = 0;
    }
    
    /**
     * Converts the binary stream to ASCII.
     * 
     * @return the String of the ASCII representation of the binary message
     * @throws IllegalArgumentException - if the last character is not 8 bits
     * @throws NumberFormatException - if the binary String contains any
     * character other than '0' and '1'
     */
    public String decode()
            throws IllegalArgumentException, NumberFormatException { 
        
        StringBuilder binary = new StringBuilder("");
        int decimal = 0;
        int index = 0;
        this.lineNumber = 0;
        while (input.hasNext()) {
            String message = input.next();
            int messlen = message.length();
            this.lineNumber++;
            if (this.lineNumber > 1) {
                binary.append("\n");//spaces are 00000000, a new line is 12 in decimal
            }
            if (messlen % 8 != 0) {
                throw new IllegalArgumentException();// throws if not 8 bits, thats where you separate characters
            } index = 0;
            while (index < messlen) {
                String binaryByte = message.substring(index, index + 8);
                if (messlen() % 8 != 0) {
                    throw new IllegalArgumentException("Error: The last character is not 8 bits.");
                } else {
                    decimal = binaryToDecimal(binaryByte);
                }
                binary.append(Character.toString((char) decimal));// characters and integers are interchangable
                index += 8; //goes to next decimal in binary
            }
        }
        
        input.close();
        binaryStr = binary.toString();
        return binaryStr;
    }
    
    /**
     * Converts a binary String representation into a decimal value.
     * 
     * @param binaryString the binary representation of a decimal value
     * 
     * @return the decimal (integer) value of a binary number
     * @throws NumberFormatException - if the binary String contains any
     * character other than '0' and '1'
     */
    public static int binaryToDecimal(String binaryString)
            throws NumberFormatException {
        int ASCII = 0;
        int binstrlen = binaryString.length();
        for (int i = 0; i < binstrlen; i++) {
            if (binaryString.charAt(i) != '0') {
                if (binaryString.charAt(i) != '1') {
                    throw new NumberFormatException();
                }
                ASCII += Math.pow(2, binstrlen - i - 1);
            }
        }
        return ASCII;
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public static void main (String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java BinaryDecoder <input file>");
            System.exit(1);
        }
        BinaryDecoder bd = null;
        try {
            bd = new BinaryDecoder(args[0]);
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error: File '" + args[0] + "' not found.");
            System.exit(1);
        }
        try {
            System.out.println(bd.decode());
        } catch (NumberFormatException nfe) {
            System.err.println(":NumberFormatException.");
            System.exit(1);
        } catch (IllegalArgumentException iae) {
            System.err.println("Error: Invalid character at the end of line " + new Integer(bd.getLineNumber()).toString() + ".");
            System.exit(1);
        }
        System.exit(0);
    }
}
