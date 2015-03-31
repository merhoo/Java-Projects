
public class MyRectangle2D {
    private double x, y, width, height;
    
    public MyRectangle2D() {
        this(0.0,0.0,1.0,2.0);
    }
    
    public MyRectangle2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
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
    
    public double getPerimeter() {
        double perimeter = 2*height + 2*width;
        return perimeter;
    }
    
    public boolean contains(double x, double y) {
        double rectX = getX(), rectY = getY(),
                leftBound = rectX - getWidth()/2,
                rightBound = rectX + getWidth()/2,
                topBound = rectY + getHeight()/2,
                bottomBound = rectY - getHeight()/2;
        if (x <= rightBound && x >= leftBound) {
            if (y <= topBound && y >= bottomBound) {
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(MyRectangle2D r) {
        double x = r.x,
               y = r.y,
               rLeftBound = x - r.width/2,
               rRightBound = x + r.width/2,
               rTopBound = y + r.height/2,
               rBottomBound = y - r.height/2;
        double rectX = getX(),
               rectY = getY(),
               leftBound = rectX - getWidth()/2,
               rightBound = rectX + getWidth()/2,
               topBound = rectY + getHeight()/2,
               bottomBound = rectY - getHeight()/2;
        if (contains(x, y)) {
            if (rRightBound <= rightBound &&
                    rLeftBound >= leftBound &&
                    rTopBound <= topBound &&
                    rBottomBound >= bottomBound) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean overlaps(MyRectangle2D r) {
        double x = r.x,
                y = r.y,
                rLeftBound = x - r.width/2,
                rRightBound = x + r.width/2,
                rTopBound = y + r.height/2,
                rBottomBound = y - r.height/2;
         double rectX = getX(),
                rectY = getY(),
                leftBound = rectX - getWidth()/2,
                rightBound = rectX + getWidth()/2,
                topBound = rectY + getHeight()/2,
                bottomBound = rectY - getHeight()/2;
         if (rLeftBound <= rightBound &&
                 rRightBound >= leftBound &&
                 rBottomBound <= topBound &&
                 rTopBound >= bottomBound) {
             return true;
         }
        return false;
    }
    
    
}
