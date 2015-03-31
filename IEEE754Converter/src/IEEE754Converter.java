
public class IEEE754Converter {
    public static final int
        SINGLE_PRECISION = 0,
        DOUBLE_PRECISION = 1;

    public static final String[]
        INFINITY_EXPONENT = { "11111111", "11111111111" },
        ZERO_EXPONENT = { "00000000", "00000000000" },
        INFINITY_MANTISSA = {"00000000000000000000000", "0000000000000000000000000000000000000000000000000000"},
        ZERO_MANTISSA = {"00000000000000000000000", "0000000000000000000000000000000000000000000000000000"},
        NOTNUM_MANTISSA = {"10000000000000000000000", "1000000000000000000000000000000000000000000000000000"};
    public static final int[]
        NUM_EXPONENT_BITS = { 8, 11 },
        NUM_MANTISSA_BITS ={23, 52},
        NUM_BITS = { 32, 64 },
        BIAS = { 127, 1023 };
    
    
    private int conversionType, signBit;
    private double value;
    private String exponentStr, mantissaStr;
    
    public IEEE754Converter(double value, int conversionType) {
        this.conversionType = conversionType;
        if (conversionType == SINGLE_PRECISION) {
            value = (float)value;
        }
        this.value = value;
        computeSignBit();
        computeExponentMantissa();
    }
    
    public double getValue() {
        return value;
    }
    
    public String getSignBitString() {
        return String.valueOf(signBit);
    }
    
    public String getExponentString() {
        return exponentStr;
    }
    
    public String getMantissaString() {
        return mantissaStr;
    }
    
    public String getRepresentation() {
        StringBuilder builder = new StringBuilder();
        if (conversionType == SINGLE_PRECISION) {
            builder.append("Single-precision ");
        } else {
            builder.append("Double-precision ");
        }
        builder.append("(" + NUM_BITS[conversionType] + "-bit):");
        return builder.toString();
    }
    
    private void computeSignBit() {
        signBit = Double.compare(value, -0.0) == 0 || value < 0 ? 1 : 0;
    //    if (Double.compare(value, -0.0) || value < 0) {
    //        signBit = 1;
    //    } else {
    //        signBit = 0;
    //    }
    }
    
    /**
    * Computes the exponent and mantissa. Upon completion of this method,
    * exponentStr and mantissaStr will be assigned String values corresponding
    * to the bit patterns for representing the floating-point number.
    * 
    * If the value is infinite:
    *   exponentStr will be all 1s
    *   mantissaStr will be all 0s
    *   
    * If the value is NaN (not-a-number):
    *   exponentStr will be all 1s
    *   mantissaStr can be any non-zero value
    *   To simplify the process, make mantissaStr = 100000... (one 1, the rest 
    *   0s)
    *   
    * If the value is zero:
    *   exponentStr will be all 0s
    *   mantissaStr will be all 0s
    *   
    * In all other cases, proceed to run the algorithm to convert
    * floating-point numbers to IEEE 754 representation.
    */
    private void computeExponentMantissa() {
        double b = Math.abs(getValue());
        if (Double.isInfinite(value)) {
            exponentStr = INFINITY_EXPONENT[conversionType];
            mantissaStr = ZERO_MANTISSA[conversionType];
        } else if (Double.isNaN(value)) {
            exponentStr = INFINITY_EXPONENT[conversionType];
            mantissaStr = NOTNUM_MANTISSA[conversionType];
        } else if (value == 0) {
            exponentStr = ZERO_EXPONENT[conversionType];
            mantissaStr = ZERO_MANTISSA[conversionType];
        } else {
            int expo = 0;
            if (b >= 2) {
                for (; b >=2; b /= 2, expo++) {
                    ;
                }
            } else if (b < 1) {
                for (; b < 1; b *= 2, expo--) {
                    ;
                }
            } expo += BIAS[conversionType];
            exponentStr = decimalToBinary(expo, NUM_EXPONENT_BITS[conversionType]);
            b -= 1;
            StringBuilder mantis = new StringBuilder();
            while (mantis.length() < NUM_MANTISSA_BITS[conversionType]) {
                while (b != 0.0) {
                    b *= 2;
                    if (b < 1) {
                        mantis.append("0");
                    }
                    if (b >= 1) {
                        mantis.append("1");
                        b -= 1;
                    }    
                } 
                if (mantis.length() < NUM_MANTISSA_BITS[conversionType]) {
                    mantis.append("0");
                }
            }
            mantissaStr = mantis.toString();
        }
    }
    
    /**
    * Checks if a value is within the range 1 <= n < 2.
    * @param value the value to check
    * @return whether or not the value is in range
    */
    private boolean valueInRange(double value) {
        if (1 <= value) {
            if (value < 2) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Converts a decimal value into a binary String representation.
    * @param value the base 10 value to convert to binary
    * @param numberOfBits the number of bits the binary representation must
    * contain
    * @return the String of a binary representation of value
    * @throws NumberFormatException - if the value is less than 0
    */
    public static String decimalToBinary(long value, int numberOfBits)
        throws NumberFormatException {
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
    
    /**
    * Returns a String representation of the bit patterns in memory using
    * ASCII art.
    */
    public String toString() {
        StringBuilder str = new StringBuilder();
        System.out.println(getRepresentation());
        for (int i = 0; i < NUM_BITS[conversionType] + 4; i++) {
            str.append("-");
        }
        str.append("\n|" + signBit + "|" + exponentStr + "|" + mantissaStr + "|\n");
        
        for (int i = 0; i < NUM_BITS[conversionType] + 4; i++) {
            str.append("-");
        }
        return str.toString();
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java IEEE754Converter <value> <conversion type>");
            System.err.println(
                "   <conversion type> = S for single-precision, D for double-precision");
            System.exit(1);
        }
        double value  = 0.0;
        try {
            value = Double.parseDouble(args[0]);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: Value '" + args[0] + "' is not a valid floating-point number.");
            System.exit(1);
        }
        String conversionTypeStr = args[1];
        int conversionType = IEEE754Converter.DOUBLE_PRECISION;
        if (conversionTypeStr.equalsIgnoreCase("s")) {
            conversionType = IEEE754Converter.SINGLE_PRECISION;
        } else if (!conversionTypeStr.equalsIgnoreCase("d")) {
            System.err.println("Error: Invalid conversion type '" + conversionTypeStr + "'.");
            System.exit(1);
        }
        IEEE754Converter converter = new IEEE754Converter(value, conversionType);
        System.out.println(converter);
        System.out.println("Decimal value: " + converter.getValue());
    }
}
