package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Square class.
 */
public class SquareTest {

    @Test
    public void testConstructor() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        assertNotNull(square);
        assertEquals("square1", square.getName());
        assertEquals(10.0, square.getX(), 0.001);
        assertEquals(20.0, square.getY(), 0.001);
        assertEquals(30.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testConstructorWithZeroValues() {
        Square square = new Square("square2", 0.0, 0.0, 0.0);
        assertEquals("square2", square.getName());
        assertEquals(0.0, square.getX(), 0.001);
        assertEquals(0.0, square.getY(), 0.001);
        assertEquals(0.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testConstructorWithNegativeValues() {
        Square square = new Square("square3", -10.0, -20.0, 30.0);
        assertEquals("square3", square.getName());
        assertEquals(-10.0, square.getX(), 0.001);
        assertEquals(-20.0, square.getY(), 0.001);
        assertEquals(30.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testConstructorWithLargeValues() {
        Square square = new Square("square4", 1000.0, 2000.0, 3000.0);
        assertEquals("square4", square.getName());
        assertEquals(1000.0, square.getX(), 0.001);
        assertEquals(2000.0, square.getY(), 0.001);
        assertEquals(3000.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testConstructorWithDecimalValues() {
        Square square = new Square("square5", 10.5, 20.75, 30.25);
        assertEquals("square5", square.getName());
        assertEquals(10.5, square.getX(), 0.001);
        assertEquals(20.75, square.getY(), 0.001);
        assertEquals(30.25, square.getSideLength(), 0.001);
    }

    @Test
    public void testGetName() {
        Square square = new Square("testSquare", 1.0, 2.0, 3.0);
        assertEquals("testSquare", square.getName());
    }

    @Test
    public void testGetNameWithEmptyString() {
        Square square = new Square("", 1.0, 2.0, 3.0);
        assertEquals("", square.getName());
    }

    @Test
    public void testGetNameWithSpecialCharacters() {
        Square square = new Square("square_123", 1.0, 2.0, 3.0);
        assertEquals("square_123", square.getName());
    }

    @Test
    public void testGetType() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        assertEquals("square", square.getType());
    }

    @Test
    public void testGetX() {
        Square square = new Square("square1", 15.5, 20.0, 30.0);
        assertEquals(15.5, square.getX(), 0.001);
    }

    @Test
    public void testGetY() {
        Square square = new Square("square1", 10.0, 25.5, 30.0);
        assertEquals(25.5, square.getY(), 0.001);
    }

    @Test
    public void testGetSideLength() {
        Square square = new Square("square1", 10.0, 20.0, 35.5);
        assertEquals(35.5, square.getSideLength(), 0.001);
    }

    @Test
    public void testToString() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        String result = square.toString();
        assertTrue(result.contains("square"));
        assertTrue(result.contains("square1"));
        assertTrue(result.contains("10.00"));
        assertTrue(result.contains("20.00"));
        assertTrue(result.contains("30.00"));
    }

    @Test
    public void testToStringWithDecimalValues() {
        Square square = new Square("square2", 10.123, 20.456, 30.789);
        String result = square.toString();
        assertTrue(result.contains("square2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("30.79"));
    }

    @Test
    public void testToStringWithZeroValues() {
        Square square = new Square("square3", 0.0, 0.0, 0.0);
        String result = square.toString();
        assertTrue(result.contains("square3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testToStringWithNegativeValues() {
        Square square = new Square("square4", -10.0, -20.0, 30.0);
        String result = square.toString();
        assertTrue(result.contains("square4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
    }

    @Test
    public void testImplementsShape() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        assertTrue(square instanceof Shape);
    }
}
