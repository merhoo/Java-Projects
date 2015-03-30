import java.util.Scanner;

public class ComputeCircleStats {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double radius;
        System.out.print("Enter the radius of the circle: ");
        radius = input.nextDouble();
        System.out.println("Radius is " + radius);
        double area = radius * radius * Math.PI;
        System.out.println("Area is " + area);
        double circumference = 2 * radius * Math.PI;
        System.out.println("Circumference is " + circumference);
        System.out.println("Enter your first and last name: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("Hello " + name);
        input.close();
        float degrees = 37.333333333f;
        System.out.printf("%.2f Degrees F \n", degrees);//% is the place holder//
        // %d is for integers, %f is for floats and doubles, %s is for strings//
        int i = 5, j = 6;
        System.out.printf("i = %d\n", i);//two % creates a % sign
        System.out.printf("j = %d\n", j);
    }

}
