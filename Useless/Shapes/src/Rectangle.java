
public class Rectangle extends Shape{//sub-class
    
    private double width, height;
    
    public Rectangle() {
        super("Rectangle", 0.0, 0.0);//calling constructor in Shape class
        width = height = 1.0;
    }
    public Rectangle(double x, double y, double width, double height) {
        super("Rectangle", x, y);
        this.width = width;
        this.height = height;
    }
    public Rectangle(String name, double x, double y, double width, double height) {
        super(name, x, y);
        this.width = width;
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getArea() {
        double area = height*width;
        return area;
    }
    @Override
    public boolean equals(Object o) {//checks the equality of references
        if (o instanceof Rectangle) {
            Rectangle r = (Rectangle)o;//casting it from an object to a rectangle
            return super.equals(r) && this.height == r.height && this.width == r.width;
        }
       return false;
    }
    public String toString() {
        return super.toString() + ", with dimensions " + width + " x " + height; 
    }
}
