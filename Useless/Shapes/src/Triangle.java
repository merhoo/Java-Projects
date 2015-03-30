
public class Triangle extends Shape{
    private double base, height;
    
    public Triangle() {
        super("Triange", 0.0, 0.0);
        base = height = 1.0;
    }
    
    public Triangle(double x, double y, double base, double height) {
        super("Triangle", x, y);
        this.base = base;
        this.height = height;
    }
    
    public Triangle(String name, double x, double y, double base, double height) {
        super(name, x , y);
        this.base = base;
        this.height = height;
    }
    
    public double getBase() {
        return base;
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setBase(double base) {
        this.base = base;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public double getArea() {
        double area = base * height /2;
        return area;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Triangle) {
            Triangle t = (Triangle)o;
            return super.equals(t) && this.height == t.height && this.base == t.base;
        }
        return false;
    }
    
    public String toString() {
        return super.toString() + ", with dimensions " + base + " x " + height;
    }
}
