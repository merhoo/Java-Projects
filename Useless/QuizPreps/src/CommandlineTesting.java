
public class CommandlineTesting {
    /**
     * Command line arguments
     * Parse ints or doubles
     * Array of ints or doubles
     * StringBuilder
     * println/printf
     * By James Brancale
     */
    
    static double product = 1.0;
    
    public static String sumIntArray(int[] input) {
        int sum = 0;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            
            if (i != input.length - 1) {
                str.append(input[i] + " + ");
            } else {
                str.append(input[i] + " ");
            }
            
            sum += input[i];
        }
        
        str.append("= " + sum);
        
        return str.toString();
    }
    
    public static String productDoubleArray(double[] input) {
        
        boolean runProduct = false;
        StringBuilder productOfElements = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            
            if (i != input.length - 1) {
                productOfElements.append(input[i] + " * ");
            } else {
                productOfElements.append(input[i] + " ");
            }
            
            product *= input[i];
            runProduct = true;
        }
        
        if (runProduct == false) {
            product = 0.0;
        }
        
        //productOfElements.append("= " + product); //Note, you would need Double.toString for each double element if this was NOT StringBuilder
        
        return productOfElements.toString();
    }
    
    public static void main(String[] args) {
        String[] arg0 = args[0].split("\\s+");
        int[] args0 = new int[arg0.length];
        for (int i = 0; i < arg0.length; i++) {
            try {
                args0[i] = Integer.parseInt(arg0[i]);
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Invalid String argument cannot be converted to type int.");
                System.exit(1);
            }
        }
        System.out.println("Sum: " + sumIntArray(args0));
        
        String[] arg1 = args[1].split("\\s+");
        double[] args1 = new double[arg1.length];
        for (int j = 0; j < arg1.length; j++) {
            try {
                args1[j] = Double.parseDouble(arg1[j]);
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Invalid String argument cannot be converted to type double.");
                System.exit(1);
            }
        }
        System.out.printf("Product: " + productDoubleArray(args1) + "= %.2f", product);
    }
}
