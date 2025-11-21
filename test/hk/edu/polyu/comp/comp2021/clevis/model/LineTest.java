package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Line class.
 */
public class LineTest {

    @Test
    public void testConstructor() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        assertNotNull(line);
        assertEquals("line1", line.getName());
        assertEquals(0.0, line.getX1(), 0.001);
        assertEquals(0.0, line.getY1(), 0.001);
        assertEquals(10.0, line.getX2(), 0.001);
        assertEquals(10.0, line.getY2(), 0.001);
    }

    @Test
    public void testConstructorWithZeroValues() {
        Line line = new Line("line2", 0.0, 0.0, 0.0, 0.0);
        assertEquals("line2", line.getName());
        assertEquals(0.0, line.getX1(), 0.001);
        assertEquals(0.0, line.getY1(), 0.001);
        assertEquals(0.0, line.getX2(), 0.001);
        assertEquals(0.0, line.getY2(), 0.001);
    }

    @Test
    public void testConstructorWithNegativeValues() {
        Line line = new Line("line3", -10.0, -20.0, -5.0, -15.0);
        assertEquals("line3", line.getName());
        assertEquals(-10.0, line.getX1(), 0.001);
        assertEquals(-20.0, line.getY1(), 0.001);
        assertEquals(-5.0, line.getX2(), 0.001);
        assertEquals(-15.0, line.getY2(), 0.001);
    }

    @Test
    public void testConstructorWithMixedPositiveNegative() {
        Line line = new Line("line4", -10.0, 20.0, 10.0, -20.0);
        assertEquals("line4", line.getName());
        assertEquals(-10.0, line.getX1(), 0.001);
        assertEquals(20.0, line.getY1(), 0.001);
        assertEquals(10.0, line.getX2(), 0.001);
        assertEquals(-20.0, line.getY2(), 0.001);
    }

    @Test
    public void testConstructorWithLargeValues() {
        Line line = new Line("line5", 1000.0, 2000.0, 3000.0, 4000.0);
        assertEquals("line5", line.getName());
        assertEquals(1000.0, line.getX1(), 0.001);
        assertEquals(2000.0, line.getY1(), 0.001);
        assertEquals(3000.0, line.getX2(), 0.001);
        assertEquals(4000.0, line.getY2(), 0.001);
    }

    @Test
    public void testConstructorWithDecimalValues() {
        Line line = new Line("line6", 10.5, 20.75, 30.25, 40.125);
        assertEquals("line6", line.getName());
        assertEquals(10.5, line.getX1(), 0.001);
        assertEquals(20.75, line.getY1(), 0.001);
        assertEquals(30.25, line.getX2(), 0.001);
        assertEquals(40.125, line.getY2(), 0.001);
    }

    @Test
    public void testGetName() {
        Line line = new Line("testLine", 1.0, 2.0, 3.0, 4.0);
        assertEquals("testLine", line.getName());
    }

    @Test
    public void testGetNameWithEmptyString() {
        Line line = new Line("", 1.0, 2.0, 3.0, 4.0);
        assertEquals("", line.getName());
    }

    @Test
    public void testGetNameWithSpecialCharacters() {
        Line line = new Line("line_123", 1.0, 2.0, 3.0, 4.0);
        assertEquals("line_123", line.getName());
    }

    @Test
    public void testGetType() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        assertEquals("line", line.getType());
    }

    @Test
    public void testGetX1() {
        Line line = new Line("line1", 15.5, 20.0, 30.0, 40.0);
        assertEquals(15.5, line.getX1(), 0.001);
    }

    @Test
    public void testGetY1() {
        Line line = new Line("line1", 10.0, 25.5, 30.0, 40.0);
        assertEquals(25.5, line.getY1(), 0.001);
    }

    @Test
    public void testGetX2() {
        Line line = new Line("line1", 10.0, 20.0, 35.5, 40.0);
        assertEquals(35.5, line.getX2(), 0.001);
    }

    @Test
    public void testGetY2() {
        Line line = new Line("line1", 10.0, 20.0, 30.0, 45.5);
        assertEquals(45.5, line.getY2(), 0.001);
    }

    @Test
    public void testToString() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        String result = line.toString();
        assertTrue(result.contains("line"));
        assertTrue(result.contains("line1"));
        assertTrue(result.contains("0.00"));
        assertTrue(result.contains("10.00"));
    }

    @Test
    public void testToStringWithDecimalValues() {
        Line line = new Line("line2", 10.123, 20.456, 30.789, 40.012);
        String result = line.toString();
        assertTrue(result.contains("line2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("30.79"));
        assertTrue(result.contains("40.01"));
    }

    @Test
    public void testToStringWithZeroValues() {
        Line line = new Line("line3", 0.0, 0.0, 0.0, 0.0);
        String result = line.toString();
        assertTrue(result.contains("line3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testToStringWithNegativeValues() {
        Line line = new Line("line4", -10.0, -20.0, -5.0, -15.0);
        String result = line.toString();
        assertTrue(result.contains("line4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
        assertTrue(result.contains("-5.00"));
        assertTrue(result.contains("-15.00"));
    }

    @Test
    public void testImplementsShape() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        assertTrue(line instanceof Shape);
    }
}
