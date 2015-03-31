
public class QuadraticEquation {
    private double a, b, c;
    
    public QuadraticEquation(double a, double b, double c) throws IllegalArgumentException {
        if (a == 0) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be 0 in a quadratic equation.");
        }
        this.a = a;//////////////////////////public String toString() {}
        this.b = b;
        this.c = c;
    }
    public double getA(double a) {
        return this.a;
    }
    public double getB(double b) {
        return this.b;
    }
    public double getC(double c) {
        return this.c;
    }
    public double getDiscriminant() {
        double discriminant = (b*b) - (4.0 * a * c);
        return discriminant;
    }
    public double getRoot1() {
        double discriminant = getDiscriminant(), root1;
        if (discriminant < 0.0) {
            return 0.0;
        }
        else {
            root1 = ((-b + Math.sqrt(discriminant))) / (2.0 * a);
            if (root1 == -0.0) {
                return 0.0;
            } else {
                return root1;
            }            
        }
    }
    public double getRoot2() {
        double discriminant = getDiscriminant(), root2;
        if (discriminant < 0.0) {
            return 0.0;
        }
        else {
            root2 = ((-b - Math.sqrt(discriminant))) / (2.0 * a);
            if (root2 == -0.0) {
                return 0.0;
            } else {
                return root2;
            }            
        }
    }
    public String toString() {
        String equation = "";
        if (a > 0) {
            if (a == 1) {
                equation += "x^2 "; 
            }
            else {
                equation += a + "x^2 ";
            }
        } 
        if (a < 0) {
            if (a == -1) {
                equation += "-1.0x^2 ";
            } else {
                equation += a + "x^2 ";
            }
        }
        if (b > 0) {
            if (b == 1) {
                equation += "+ x ";
            } else {
                equation += "+ " + b + "x ";
            }
        
            
        }
        if (b < 0) {
            if (b == -1) {
                equation += "- x ";
            } else {
                equation += "- " + Math.abs(b) + "x ";
            }
        }
        if (c > 0) {
            equation += "+ " + c + " ";
                        
        }
        if (c < 0) {
            equation += "- " + Math.abs(c) + " ";
        }
        equation += "= 0";
        return equation;
    }
    public static void main(String[] args) {
        
        if (args.length != 3) {
            System.out.println("Usage: java QuadraticEquation <a> <b> <c>");
            System.exit(1);
        }
        
        double a = 0.0, b = 0.0, c = 0.0;
        try {
            a = Double.parseDouble(args[0]);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: Invalid value '" + args[0] + "' for a.");
            System.exit(1);
        }
        try {
            b = Double.parseDouble(args[1]);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: Invalid value '" + args[1] + "' for b.");
            System.exit(1);
        }
        try {
            c = Double.parseDouble(args[2]);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: Invalid value '" + args[2] + "' for c.");
            System.exit(1);
        }
        QuadraticEquation qe = null;
        try {
            qe = new QuadraticEquation(a, b, c); 
        } catch (IllegalArgumentException iae) {
            System.err.println("Error: " + iae.getMessage());
            System.exit(1);
        }
        double discriminant = qe.getDiscriminant();
        double root1 = qe.getRoot1();
        double root2 = qe.getRoot2();
        String equation = qe.toString();
        System.out.println(equation);
        System.out.printf("Discriminant: %.6f\n", discriminant);
        if (root1 == 0 && root2 == 0 && b != 0 && c != 0) {
                System.out.println("The equation has no real roots.");
        } else {
            System.out.printf("Root 1: %.6f\n", root1);
            System.out.printf("Root 2: %.6f\n", root2);
        }
    }
}
