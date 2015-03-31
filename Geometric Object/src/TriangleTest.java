import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TriangleTest {
    private static final double EPSILON = 10e-6;

    @Test
    public void test1() {
        Triangle triangle = new Triangle();
        assertTrue(
                "Dates must be within 1 second window.",
                Math.abs((new Date()).getTime() - triangle.getDateCreated().getTime()) < 1000);
        assertEquals("getSide1()", 1.0, triangle.getSide1(), EPSILON);
        assertEquals("getSide2()", 1.0, triangle.getSide2(), EPSILON);
        assertEquals("getSide3()", 1.0, triangle.getSide3(), EPSILON);
        assertEquals("getArea()", 0.4330127018922193, triangle.getArea(), EPSILON);
        assertEquals("getPerimeter()", 3.0, triangle.getPerimeter(), EPSILON);
        assertEquals("toString()", "Triangle: side1 = 1.0 side2 = 1.0 side3 = 1.0", triangle.toString());
        assertFalse("getFilled()", triangle.isFilled());
        assertEquals("getColor()", "white", triangle.getColor());
    }

    @Test
    public void test2() {
        Triangle triangle = new Triangle(4.0, 2.2, 3.5);
        assertTrue(
                "Dates must be within 1 second window.",
                Math.abs((new Date()).getTime() - triangle.getDateCreated().getTime()) < 1000);
        assertEquals("getSide1()", 4.0, triangle.getSide1(), EPSILON);
        assertEquals("getSide2()", 2.2, triangle.getSide2(), EPSILON);
        assertEquals("getSide3()", 3.5, triangle.getSide3(), EPSILON);
        assertEquals("getArea()", 3.840344222852944, triangle.getArea(), EPSILON);
        assertEquals("getPerimeter()", 9.7, triangle.getPerimeter(), EPSILON);
        assertEquals("toString()", "Triangle: side1 = 4.0 side2 = 2.2 side3 = 3.5", triangle.toString());
        assertFalse("getFilled()", triangle.isFilled());
        assertEquals("getColor()", "white", triangle.getColor());
    }

    @Test
    public void test3() {
        Triangle triangle = new Triangle(3.1, 3.3, 5.5, "green", true);
        assertTrue(
                "Dates must be within 1 second window.",
                Math.abs((new Date()).getTime() - triangle.getDateCreated().getTime()) < 1000);
        assertEquals("getSide1()", 3.1, triangle.getSide1(), EPSILON);
        assertEquals("getSide2()", 3.3, triangle.getSide2(), EPSILON);
        assertEquals("getSide3()", 5.5, triangle.getSide3(), EPSILON);
        assertEquals("getArea()", 4.4968676598272275, triangle.getArea(), EPSILON);
        assertEquals("getPerimeter()", 11.9, triangle.getPerimeter(), EPSILON);
        assertEquals("toString()", "Triangle: side1 = 3.1 side2 = 3.3 side3 = 5.5", triangle.toString());
        assertTrue("getFilled()", triangle.isFilled());
        assertEquals("getColor()", "green", triangle.getColor());
        triangle.setFilled(false);
        assertFalse("getFilled()", triangle.isFilled());
        triangle.setColor("red");
        assertEquals("getColor()", "red", triangle.getColor());
    }
}