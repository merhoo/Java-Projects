
public class ClassesQuiz {//class is a blueprint for modeling something(object)
    //format file as .java
    //"MyPoint point;" is the declaration and "point" is the object reference (variable)
    //new MyPoint(); is the process of instantiation
    //point = new MyPoint(); or MyPoint point2 = new MyPoint, this declares, instantiates and initializes an object
    //Constructor is a method that never returns anything, it initializes the fields, must match name of class
    //is it possible to throw an exception if errors occur
    //when method is public, the method is accessible anywhere, when private, only accessible in that class
    //you need a static when it applies to all objects of that class, does not go well with unique aspects of objects
    //all classes provide a constructor if there isn't one with no arguments
    //you can have multiple constructors, as long as they take in different arguments ex. floats, ints, two arguments
    //
    public static void main(String[] args) {
        A a = new A();
        a.print();
        //System.out.println(f.s);
    }
}
class A{
    String s;//field that is an object initialized to null
    int i;//field that is an object initialized to 0;
    A(String s) {
        this.s = s;
    }
    A() {
        
    }
    public void print() {
        System.out.print(s);
        System.out.print(i);
    }
}
/*
public class Square {
    private double length;
    
    public Square(double length) {
        this.length = length;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        
    }
    public static void main(String[] args) {
        Square s = new Square(6.7);
        
        
    }
}
*/
/*public class Foo {
    int i;
    static String s;
    void imethod() {
        
    }
    static void smethod() {
        
    }
}//static method cannot reference, instance variable inside static method
*/