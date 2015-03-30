
public class Circle extends Shape {
    private double radius;
    
    public Circle(){
        super("Circle", 0.0, 0.0);
    }
    public Circle(String name, double x, double y, double radius) {
        super(name, x, y);
        this.radius = radius;
    }
    public Circle(double radius) {
        super("Circle", 0.0, 0.0);
        this.radius = 1.0;
    }
    public Circle(double radius, double x, double y) {
        this("Circle", x, y, radius);
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getArea() {
        double area = radius * radius * Math.PI;
        return area;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Circle) {
            Circle c = (Circle)o;//casting it from an object to a circle
            return super.equals(c) && this.radius == c.radius;
        }
       return false;
    }
    @Override
    public String toString() {
        return super.toString() + ", with radius of " + radius;
    }
}
