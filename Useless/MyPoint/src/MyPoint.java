
public class MyPoint {
    private int x, y;//fields are part of a class that represents something'
    public static final int Z = 4;// this cannot be changed but can be accessed
    
    public MyPoint() {//constructor- initializes the fields
        x = y = 0;
    }
    public MyPoint(int x, int y) {//constructor
        this.x = x;//this.x refers to the private field
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return this.x;
    }
    public static void main(String[] args) {
        MyPoint point1= new MyPoint();//declaration is "MyPoint point1", is equal to (0,0)
        MyPoint point2 = new MyPoint(2, 3);// point1 and point2 are object value references
        //instantiation = process of assigning an object value to an object reference//
        point1 = point2;// When there are no more references to an object value, that value is eligible for garbage collection
        point2.setX(5);
        System.out.println(point1 + " and " + point2);
    }

}
