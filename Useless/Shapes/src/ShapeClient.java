
public class ShapeClient {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(2.0, 1.0, 3.0, 5.2);
        System.out.println(r);
        //dynamic binding: at run time, Java looks up which method to invoke starting at the lowest level in the inheritance hierarchy
        Circle c = new Circle(7.0, 2.0, 3.0);
        Square sq = new Square(9.0, 8.0, 5.0);
        Shape[] shapes = {r, c, sq};
        for (Shape s: shapes) {
            System.out.println(s.getArea());
        }
    }
}
