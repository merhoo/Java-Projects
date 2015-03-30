
public abstract class Shape {
    private String name;
    private double x;
    private double y;
    
    /*
     * No-args constructor
     * Constructors cannot have a return type
     * They can throw exceptions
     */
    public Shape() {
        this("Shape", 0.0, 0.0);//calls alternate constructor
        //Constructor chaining: process by which one constructor calls another
        //A default, no-args constructor is provided if you do not write your own
        //Once you write your own the default, no args constructor disappears
    }
    public Shape(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public String getName() {
        return name;
        //This method is a getter, get method or an Accessor
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setName(String name) {
        this.name = name;
        //Setter/Mutator changes the state the object is in or the instance
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    //override the objects class
    @Override
    public String toString() {
        return name + " at (" + x + ", " + y + ")";
        //returns a string representation of object
    }
    
    public abstract double getArea();//no body
    
    @Override
    public boolean equals(Object o) {//signature is Object o, so that you can override it
        if (o instanceof Shape) {
            Shape shape = (Shape)o;//casting it from an object to a rectangle
            return this.name.equals(shape.name) && this.x == shape.x && this.y == shape.y;
        }
       return false;
    }
    //can't do ==, check values are same, not point of reference
   public static void main(String[] args) {
       
   }
}
