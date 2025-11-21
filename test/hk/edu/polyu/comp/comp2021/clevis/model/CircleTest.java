package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Circle class.
 */
public class CircleTest {

    @Test
    public void testConstructor() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        assertNotNull(circle);
        assertEquals("circle1", circle.getName());
        assertEquals(10.0, circle.getX(), 0.001);
        assertEquals(20.0, circle.getY(), 0.001);
        assertEquals(5.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testConstructorWithZeroValues() {
        Circle circle = new Circle("circle2", 0.0, 0.0, 0.0);
        assertEquals("circle2", circle.getName());
        assertEquals(0.0, circle.getX(), 0.001);
        assertEquals(0.0, circle.getY(), 0.001);
        assertEquals(0.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testConstructorWithNegativeValues() {
        Circle circle = new Circle("circle3", -10.0, -20.0, 5.0);
        assertEquals("circle3", circle.getName());
        assertEquals(-10.0, circle.getX(), 0.001);
        assertEquals(-20.0, circle.getY(), 0.001);
        assertEquals(5.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testConstructorWithLargeValues() {
        Circle circle = new Circle("circle4", 1000.0, 2000.0, 500.0);
        assertEquals("circle4", circle.getName());
        assertEquals(1000.0, circle.getX(), 0.001);
        assertEquals(2000.0, circle.getY(), 0.001);
        assertEquals(500.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testConstructorWithDecimalValues() {
        Circle circle = new Circle("circle5", 10.5, 20.75, 5.25);
        assertEquals("circle5", circle.getName());
        assertEquals(10.5, circle.getX(), 0.001);
        assertEquals(20.75, circle.getY(), 0.001);
        assertEquals(5.25, circle.getRadius(), 0.001);
    }

    @Test
    public void testGetName() {
        Circle circle = new Circle("testCircle", 1.0, 2.0, 3.0);
        assertEquals("testCircle", circle.getName());
    }

    @Test
    public void testGetNameWithEmptyString() {
        Circle circle = new Circle("", 1.0, 2.0, 3.0);
        assertEquals("", circle.getName());
    }

    @Test
    public void testGetNameWithSpecialCharacters() {
        Circle circle = new Circle("circle_123", 1.0, 2.0, 3.0);
        assertEquals("circle_123", circle.getName());
    }

    @Test
    public void testGetType() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        assertEquals("circle", circle.getType());
    }

    @Test
    public void testGetX() {
        Circle circle = new Circle("circle1", 15.5, 20.0, 5.0);
        assertEquals(15.5, circle.getX(), 0.001);
    }

    @Test
    public void testGetY() {
        Circle circle = new Circle("circle1", 10.0, 25.5, 5.0);
        assertEquals(25.5, circle.getY(), 0.001);
    }

    @Test
    public void testGetRadius() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 7.5);
        assertEquals(7.5, circle.getRadius(), 0.001);
    }

    @Test
    public void testToString() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        String result = circle.toString();
        assertTrue(result.contains("circle"));
        assertTrue(result.contains("circle1"));
        assertTrue(result.contains("10.00"));
        assertTrue(result.contains("20.00"));
        assertTrue(result.contains("5.00"));
    }

    @Test
    public void testToStringWithDecimalValues() {
        Circle circle = new Circle("circle2", 10.123, 20.456, 5.789);
        String result = circle.toString();
        assertTrue(result.contains("circle2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("5.79"));
    }

    @Test
    public void testToStringWithZeroValues() {
        Circle circle = new Circle("circle3", 0.0, 0.0, 0.0);
        String result = circle.toString();
        assertTrue(result.contains("circle3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testToStringWithNegativeValues() {
        Circle circle = new Circle("circle4", -10.0, -20.0, 5.0);
        String result = circle.toString();
        assertTrue(result.contains("circle4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
    }

    @Test
    public void testImplementsShape() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        assertTrue(circle instanceof Shape);
    }
}
