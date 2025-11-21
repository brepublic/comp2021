package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Rectangle class.
 */
public class RectangleTest {

    @Test
    public void testConstructor() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 40.0);
        assertNotNull(rectangle);
        assertEquals("rect1", rectangle.getName());
        assertEquals(10.0, rectangle.getX(), 0.001);
        assertEquals(20.0, rectangle.getY(), 0.001);
        assertEquals(30.0, rectangle.getWidth(), 0.001);
        assertEquals(40.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testConstructorWithZeroValues() {
        Rectangle rectangle = new Rectangle("rect2", 0.0, 0.0, 0.0, 0.0);
        assertEquals("rect2", rectangle.getName());
        assertEquals(0.0, rectangle.getX(), 0.001);
        assertEquals(0.0, rectangle.getY(), 0.001);
        assertEquals(0.0, rectangle.getWidth(), 0.001);
        assertEquals(0.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testConstructorWithNegativeValues() {
        Rectangle rectangle = new Rectangle("rect3", -10.0, -20.0, 30.0, 40.0);
        assertEquals("rect3", rectangle.getName());
        assertEquals(-10.0, rectangle.getX(), 0.001);
        assertEquals(-20.0, rectangle.getY(), 0.001);
        assertEquals(30.0, rectangle.getWidth(), 0.001);
        assertEquals(40.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testConstructorWithLargeValues() {
        Rectangle rectangle = new Rectangle("rect4", 1000.0, 2000.0, 3000.0, 4000.0);
        assertEquals("rect4", rectangle.getName());
        assertEquals(1000.0, rectangle.getX(), 0.001);
        assertEquals(2000.0, rectangle.getY(), 0.001);
        assertEquals(3000.0, rectangle.getWidth(), 0.001);
        assertEquals(4000.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testConstructorWithDecimalValues() {
        Rectangle rectangle = new Rectangle("rect5", 10.5, 20.75, 30.25, 40.125);
        assertEquals("rect5", rectangle.getName());
        assertEquals(10.5, rectangle.getX(), 0.001);
        assertEquals(20.75, rectangle.getY(), 0.001);
        assertEquals(30.25, rectangle.getWidth(), 0.001);
        assertEquals(40.125, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testGetName() {
        Rectangle rectangle = new Rectangle("testRect", 1.0, 2.0, 3.0, 4.0);
        assertEquals("testRect", rectangle.getName());
    }

    @Test
    public void testGetNameWithEmptyString() {
        Rectangle rectangle = new Rectangle("", 1.0, 2.0, 3.0, 4.0);
        assertEquals("", rectangle.getName());
    }

    @Test
    public void testGetNameWithSpecialCharacters() {
        Rectangle rectangle = new Rectangle("rect_123", 1.0, 2.0, 3.0, 4.0);
        assertEquals("rect_123", rectangle.getName());
    }

    @Test
    public void testGetType() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 40.0);
        assertEquals("rectangle", rectangle.getType());
    }

    @Test
    public void testGetX() {
        Rectangle rectangle = new Rectangle("rect1", 15.5, 20.0, 30.0, 40.0);
        assertEquals(15.5, rectangle.getX(), 0.001);
    }

    @Test
    public void testGetY() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 25.5, 30.0, 40.0);
        assertEquals(25.5, rectangle.getY(), 0.001);
    }

    @Test
    public void testGetWidth() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 35.5, 40.0);
        assertEquals(35.5, rectangle.getWidth(), 0.001);
    }

    @Test
    public void testGetHeight() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 45.5);
        assertEquals(45.5, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testToString() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 40.0);
        String result = rectangle.toString();
        assertTrue(result.contains("rectangle"));
        assertTrue(result.contains("rect1"));
        assertTrue(result.contains("10.00"));
        assertTrue(result.contains("20.00"));
        assertTrue(result.contains("30.00"));
        assertTrue(result.contains("40.00"));
    }

    @Test
    public void testToStringWithDecimalValues() {
        Rectangle rectangle = new Rectangle("rect2", 10.123, 20.456, 30.789, 40.012);
        String result = rectangle.toString();
        assertTrue(result.contains("rect2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("30.79"));
        assertTrue(result.contains("40.01"));
    }

    @Test
    public void testToStringWithZeroValues() {
        Rectangle rectangle = new Rectangle("rect3", 0.0, 0.0, 0.0, 0.0);
        String result = rectangle.toString();
        assertTrue(result.contains("rect3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testToStringWithNegativeValues() {
        Rectangle rectangle = new Rectangle("rect4", -10.0, -20.0, 30.0, 40.0);
        String result = rectangle.toString();
        assertTrue(result.contains("rect4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
    }

    @Test
    public void testImplementsShape() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 40.0);
        assertTrue(rectangle instanceof Shape);
    }
}
