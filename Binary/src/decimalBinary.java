public class decimalBinary {
    //Solution by James Brancale
    public static String decimalToBinary(int value) {
        //Converts a number passed in to base 2, or binary.
        if (value == 1) {
            return "1";
        }
        return remainder(value);
    }
    
    private static String remainder(int value) {
        //Prevents the duplication of code.
        int isRemainder = value % 2;
        if (isRemainder == 1) {
            return decimalToBinary(value / 2) + "1";
        }
        return decimalToBinary(value / 2) + "0";
    }
    
    public static int binaryToDecimal(String binaryString) {
        return binaryToDecimalHelper(binaryString, 0, binaryString.length());
    }
    
    private static int binaryToDecimalHelper(String binaryString, int pos, int len) {
        if (pos < len) {
            return (binaryString.charAt(pos) - 48) *
                    (int)Math.pow(2,  len - pos - 1) +
                    binaryToDecimalHelper(binaryString, pos + 1, len);
        }
        return 0;
    }
    
    //Dr. B's solution
    public static String decimalToBinaryDrB(int value) {
        if (value <= 0) {
            return "0";
        }
        return decimalToBinaryHelper(value);
    }
    
    private static String decimalToBinaryHelper(int value) {
        if (value <= 0) {
            return "";
        }
        return decimalToBinaryHelper(value / 2) + value % 2;
    }
    
    public static void main(String[] args) {
        System.out.println(decimalToBinary(13));
        System.out.println(decimalToBinaryDrB(13));
        System.out.println(binaryToDecimal("1101"));
    }
}