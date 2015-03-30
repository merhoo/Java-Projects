
public class Square extends Rectangle {

    public Square() {
        super("Square", 0.0, 0.0, 1.0, 1.0);
    }
    
    public Square(double x, double y, double sidelength) {
        super("Square", x, y, sidelength, sidelength);
    }
    
    public Square(String name, double x, double y, double sidelength) {
        super(name, x, y, sidelength, sidelength);
    }
    
    @Override
    public void setWidth(double width) {
       super.setWidth(width);
       super.setHeight(width);
    }
    
    @Override
    public void setHeight(double height) {
        setWidth(height);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Square) {
            return super.equals(o);
        }
        return false;
    }
    
    @Override
    public String toString(){
        return super.toString(); 
    }
}
