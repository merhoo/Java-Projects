import java.util.Arrays;


public class IntAdder {
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Usage: java IntAdder <list of integers>");
            System.exit(1);
        }
        String[] userInput = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            userInput[i] = args[i];
        }
        int[] parts = new int[userInput.length];
        for (int i = 0; i < userInput.length; i++) {
            try {
                parts[i] = Integer.parseInt(userInput[i]);
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Cannot parse integer '" + userInput[i] + "'.");
                System.exit(1);
            }
        }
        Arrays.sort(parts);
        int sum = 0;        
        StringBuilder equation = new StringBuilder();
        if (parts.length == 1) {
            System.out.print(parts[0]);
            System.exit(1);
        }
        for (int j = 0; j < parts.length; j++) {
            if (j < parts.length - 1) {
                equation.append(parts[j] + " + ");
                sum += parts[j];
            }
            if (j == parts.length - 1) {
                sum += parts[j];
                equation.append(parts[j] + " = " + sum);
                break;
            }
        }
        equation.toString();
        System.out.println(equation);
        System.exit(1);
    }

}
