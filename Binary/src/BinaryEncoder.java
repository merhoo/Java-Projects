import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
/**
* The BinaryEncoder object converts messages in ASCII text to binary and writes
* the binary stream to file.
*/
public class BinaryEncoder {
	private final PrintWriter output;
	private String message;
	
/**
* Constructs a binary encoder that encodes the ASCII message in binary
* and writes the output to the file named outputFilename.
*
* @param outputFilename - the name of the output file to create
* @param message - the message to encode
*
* @throws FileNotFoundException - if the output file does not denote
* an existing, writable regular file; if a new regular file
* cannot be created; or if some other error occurs while opening or
* creating the file
* @throws IOException - if the outputFilename exists but is a
* directory rather than a regular file, does not exist but cannot be
* created, or cannot be opened for any other reason
*/
	public BinaryEncoder(String outputFilename, String message) throws FileNotFoundException, IOException{
		this.message = message;
		this.output = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
	}
	
/**
* Converts the message to binary, writes the binary to the output
* file, and closes the PrintWriter.
*/
	public void encode () {
		StringBuilder decimal = new StringBuilder();
		StringBuilder binary = new StringBuilder();
		decimal.append(message);
		String decimalString = decimal.toString();
		int decimalInt;
		String binaryString;
		for (int i = 0; i<decimalString.length(); i++) {
			decimalInt = decimalString.charAt(i);
			binary.append(decimalToBinary(decimalInt, 8));
		}
		binaryString = binary.toString();
		this.output.write(binaryString);
		this.output.close();
	}
	
/**
* Converts a decimal value into a binary String representation.
* @param value the base 10 value to convert to binary
* @param numberOfBits the number of bits the binary representation must
* contain
* @return the String of a binary representation of value
* @throws NumberFormatException - if the value is less than 0
*/
	public static String decimalToBinary(int value, int numberOfBits) throws NumberFormatException {
		StringBuilder binary = new StringBuilder();
		try {
			for (int i = 0; i < numberOfBits; i++) {
				if (value % 2 == 1) {
					binary.append(1);
				} else {
					binary.append(0);
				}
				value = value/2;
			}
		} catch (NumberFormatException nfe) {
			if (value < 0) {
				System.err.println("Error: '" + value + "' must be positive.");
			}
		}
		binary.reverse();
		String binaryReverse = binary.toString();
		return binaryReverse;
	}
	
	public static void main (String args[]) {
		if (args.length != 2) {
			System.err.println("Usage: java BinaryEncoder <output file> <message>");
			System.exit(1);
			}
		BinaryEncoder binaryEncoder = null;
		try {
			binaryEncoder = new BinaryEncoder(args[0], args[1]);
		} catch (IOException ioe) {
			System.err.println("Error: Cannot open file '" + args[0] + "' for output.");
			System.exit(1);
		}
		try {
			binaryEncoder.encode();
		} catch (IllegalArgumentException iae) {
			System.err.println("Error: " + iae.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}
}