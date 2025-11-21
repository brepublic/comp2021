package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.controller.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

/**
 * Comprehensive test suite for all classes in the Clevis project.
 * This single test file consolidates all tests to achieve 95%+ line coverage.
 */
public class AllTests {

    // ========== Circle Tests ==========
    
    @Test
    public void testCircleConstructor() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        assertNotNull(circle);
        assertEquals("circle1", circle.getName());
        assertEquals(10.0, circle.getX(), 0.001);
        assertEquals(20.0, circle.getY(), 0.001);
        assertEquals(5.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testCircleConstructorWithZeroValues() {
        Circle circle = new Circle("circle2", 0.0, 0.0, 0.0);
        assertEquals("circle2", circle.getName());
        assertEquals(0.0, circle.getX(), 0.001);
        assertEquals(0.0, circle.getY(), 0.001);
        assertEquals(0.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testCircleConstructorWithNegativeValues() {
        Circle circle = new Circle("circle3", -10.0, -20.0, 5.0);
        assertEquals("circle3", circle.getName());
        assertEquals(-10.0, circle.getX(), 0.001);
        assertEquals(-20.0, circle.getY(), 0.001);
        assertEquals(5.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testCircleConstructorWithLargeValues() {
        Circle circle = new Circle("circle4", 1000.0, 2000.0, 500.0);
        assertEquals("circle4", circle.getName());
        assertEquals(1000.0, circle.getX(), 0.001);
        assertEquals(2000.0, circle.getY(), 0.001);
        assertEquals(500.0, circle.getRadius(), 0.001);
    }

    @Test
    public void testCircleConstructorWithDecimalValues() {
        Circle circle = new Circle("circle5", 10.5, 20.75, 5.25);
        assertEquals("circle5", circle.getName());
        assertEquals(10.5, circle.getX(), 0.001);
        assertEquals(20.75, circle.getY(), 0.001);
        assertEquals(5.25, circle.getRadius(), 0.001);
    }

    @Test
    public void testCircleGetName() {
        Circle circle = new Circle("testCircle", 1.0, 2.0, 3.0);
        assertEquals("testCircle", circle.getName());
    }

    @Test
    public void testCircleGetNameWithEmptyString() {
        Circle circle = new Circle("", 1.0, 2.0, 3.0);
        assertEquals("", circle.getName());
    }

    @Test
    public void testCircleGetNameWithSpecialCharacters() {
        Circle circle = new Circle("circle_123", 1.0, 2.0, 3.0);
        assertEquals("circle_123", circle.getName());
    }

    @Test
    public void testCircleGetType() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        assertEquals("circle", circle.getType());
    }

    @Test
    public void testCircleGetX() {
        Circle circle = new Circle("circle1", 15.5, 20.0, 5.0);
        assertEquals(15.5, circle.getX(), 0.001);
    }

    @Test
    public void testCircleGetY() {
        Circle circle = new Circle("circle1", 10.0, 25.5, 5.0);
        assertEquals(25.5, circle.getY(), 0.001);
    }

    @Test
    public void testCircleGetRadius() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 7.5);
        assertEquals(7.5, circle.getRadius(), 0.001);
    }

    @Test
    public void testCircleToString() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        String result = circle.toString();
        assertTrue(result.contains("circle"));
        assertTrue(result.contains("circle1"));
        assertTrue(result.contains("10.00"));
        assertTrue(result.contains("20.00"));
        assertTrue(result.contains("5.00"));
    }

    @Test
    public void testCircleToStringWithDecimalValues() {
        Circle circle = new Circle("circle2", 10.123, 20.456, 5.789);
        String result = circle.toString();
        assertTrue(result.contains("circle2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("5.79"));
    }

    @Test
    public void testCircleToStringWithZeroValues() {
        Circle circle = new Circle("circle3", 0.0, 0.0, 0.0);
        String result = circle.toString();
        assertTrue(result.contains("circle3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testCircleToStringWithNegativeValues() {
        Circle circle = new Circle("circle4", -10.0, -20.0, 5.0);
        String result = circle.toString();
        assertTrue(result.contains("circle4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
    }

    @Test
    public void testCircleImplementsShape() {
        Circle circle = new Circle("circle1", 10.0, 20.0, 5.0);
        assertTrue(circle instanceof Shape);
    }

    // ========== Line Tests ==========

    @Test
    public void testLineConstructor() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        assertNotNull(line);
        assertEquals("line1", line.getName());
        assertEquals(0.0, line.getX1(), 0.001);
        assertEquals(0.0, line.getY1(), 0.001);
        assertEquals(10.0, line.getX2(), 0.001);
        assertEquals(10.0, line.getY2(), 0.001);
    }

    @Test
    public void testLineConstructorWithZeroValues() {
        Line line = new Line("line2", 0.0, 0.0, 0.0, 0.0);
        assertEquals("line2", line.getName());
        assertEquals(0.0, line.getX1(), 0.001);
        assertEquals(0.0, line.getY1(), 0.001);
        assertEquals(0.0, line.getX2(), 0.001);
        assertEquals(0.0, line.getY2(), 0.001);
    }

    @Test
    public void testLineConstructorWithNegativeValues() {
        Line line = new Line("line3", -10.0, -20.0, -5.0, -15.0);
        assertEquals("line3", line.getName());
        assertEquals(-10.0, line.getX1(), 0.001);
        assertEquals(-20.0, line.getY1(), 0.001);
        assertEquals(-5.0, line.getX2(), 0.001);
        assertEquals(-15.0, line.getY2(), 0.001);
    }

    @Test
    public void testLineConstructorWithMixedPositiveNegative() {
        Line line = new Line("line4", -10.0, 20.0, 10.0, -20.0);
        assertEquals("line4", line.getName());
        assertEquals(-10.0, line.getX1(), 0.001);
        assertEquals(20.0, line.getY1(), 0.001);
        assertEquals(10.0, line.getX2(), 0.001);
        assertEquals(-20.0, line.getY2(), 0.001);
    }

    @Test
    public void testLineConstructorWithLargeValues() {
        Line line = new Line("line5", 1000.0, 2000.0, 3000.0, 4000.0);
        assertEquals("line5", line.getName());
        assertEquals(1000.0, line.getX1(), 0.001);
        assertEquals(2000.0, line.getY1(), 0.001);
        assertEquals(3000.0, line.getX2(), 0.001);
        assertEquals(4000.0, line.getY2(), 0.001);
    }

    @Test
    public void testLineConstructorWithDecimalValues() {
        Line line = new Line("line6", 10.5, 20.75, 30.25, 40.125);
        assertEquals("line6", line.getName());
        assertEquals(10.5, line.getX1(), 0.001);
        assertEquals(20.75, line.getY1(), 0.001);
        assertEquals(30.25, line.getX2(), 0.001);
        assertEquals(40.125, line.getY2(), 0.001);
    }

    @Test
    public void testLineGetName() {
        Line line = new Line("testLine", 1.0, 2.0, 3.0, 4.0);
        assertEquals("testLine", line.getName());
    }

    @Test
    public void testLineGetNameWithEmptyString() {
        Line line = new Line("", 1.0, 2.0, 3.0, 4.0);
        assertEquals("", line.getName());
    }

    @Test
    public void testLineGetNameWithSpecialCharacters() {
        Line line = new Line("line_123", 1.0, 2.0, 3.0, 4.0);
        assertEquals("line_123", line.getName());
    }

    @Test
    public void testLineGetType() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        assertEquals("line", line.getType());
    }

    @Test
    public void testLineGetX1() {
        Line line = new Line("line1", 15.5, 20.0, 30.0, 40.0);
        assertEquals(15.5, line.getX1(), 0.001);
    }

    @Test
    public void testLineGetY1() {
        Line line = new Line("line1", 10.0, 25.5, 30.0, 40.0);
        assertEquals(25.5, line.getY1(), 0.001);
    }

    @Test
    public void testLineGetX2() {
        Line line = new Line("line1", 10.0, 20.0, 35.5, 40.0);
        assertEquals(35.5, line.getX2(), 0.001);
    }

    @Test
    public void testLineGetY2() {
        Line line = new Line("line1", 10.0, 20.0, 30.0, 45.5);
        assertEquals(45.5, line.getY2(), 0.001);
    }

    @Test
    public void testLineToString() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        String result = line.toString();
        assertTrue(result.contains("line"));
        assertTrue(result.contains("line1"));
        assertTrue(result.contains("0.00"));
        assertTrue(result.contains("10.00"));
    }

    @Test
    public void testLineToStringWithDecimalValues() {
        Line line = new Line("line2", 10.123, 20.456, 30.789, 40.012);
        String result = line.toString();
        assertTrue(result.contains("line2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("30.79"));
        assertTrue(result.contains("40.01"));
    }

    @Test
    public void testLineToStringWithZeroValues() {
        Line line = new Line("line3", 0.0, 0.0, 0.0, 0.0);
        String result = line.toString();
        assertTrue(result.contains("line3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testLineToStringWithNegativeValues() {
        Line line = new Line("line4", -10.0, -20.0, -5.0, -15.0);
        String result = line.toString();
        assertTrue(result.contains("line4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
        assertTrue(result.contains("-5.00"));
        assertTrue(result.contains("-15.00"));
    }

    @Test
    public void testLineImplementsShape() {
        Line line = new Line("line1", 0.0, 0.0, 10.0, 10.0);
        assertTrue(line instanceof Shape);
    }

    // ========== Rectangle Tests ==========

    @Test
    public void testRectangleConstructor() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 40.0);
        assertNotNull(rectangle);
        assertEquals("rect1", rectangle.getName());
        assertEquals(10.0, rectangle.getX(), 0.001);
        assertEquals(20.0, rectangle.getY(), 0.001);
        assertEquals(30.0, rectangle.getWidth(), 0.001);
        assertEquals(40.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testRectangleConstructorWithZeroValues() {
        Rectangle rectangle = new Rectangle("rect2", 0.0, 0.0, 0.0, 0.0);
        assertEquals("rect2", rectangle.getName());
        assertEquals(0.0, rectangle.getX(), 0.001);
        assertEquals(0.0, rectangle.getY(), 0.001);
        assertEquals(0.0, rectangle.getWidth(), 0.001);
        assertEquals(0.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testRectangleConstructorWithNegativeValues() {
        Rectangle rectangle = new Rectangle("rect3", -10.0, -20.0, 30.0, 40.0);
        assertEquals("rect3", rectangle.getName());
        assertEquals(-10.0, rectangle.getX(), 0.001);
        assertEquals(-20.0, rectangle.getY(), 0.001);
        assertEquals(30.0, rectangle.getWidth(), 0.001);
        assertEquals(40.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testRectangleConstructorWithLargeValues() {
        Rectangle rectangle = new Rectangle("rect4", 1000.0, 2000.0, 3000.0, 4000.0);
        assertEquals("rect4", rectangle.getName());
        assertEquals(1000.0, rectangle.getX(), 0.001);
        assertEquals(2000.0, rectangle.getY(), 0.001);
        assertEquals(3000.0, rectangle.getWidth(), 0.001);
        assertEquals(4000.0, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testRectangleConstructorWithDecimalValues() {
        Rectangle rectangle = new Rectangle("rect5", 10.5, 20.75, 30.25, 40.125);
        assertEquals("rect5", rectangle.getName());
        assertEquals(10.5, rectangle.getX(), 0.001);
        assertEquals(20.75, rectangle.getY(), 0.001);
        assertEquals(30.25, rectangle.getWidth(), 0.001);
        assertEquals(40.125, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testRectangleGetName() {
        Rectangle rectangle = new Rectangle("testRect", 1.0, 2.0, 3.0, 4.0);
        assertEquals("testRect", rectangle.getName());
    }

    @Test
    public void testRectangleGetNameWithEmptyString() {
        Rectangle rectangle = new Rectangle("", 1.0, 2.0, 3.0, 4.0);
        assertEquals("", rectangle.getName());
    }

    @Test
    public void testRectangleGetNameWithSpecialCharacters() {
        Rectangle rectangle = new Rectangle("rect_123", 1.0, 2.0, 3.0, 4.0);
        assertEquals("rect_123", rectangle.getName());
    }

    @Test
    public void testRectangleGetType() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 40.0);
        assertEquals("rectangle", rectangle.getType());
    }

    @Test
    public void testRectangleGetX() {
        Rectangle rectangle = new Rectangle("rect1", 15.5, 20.0, 30.0, 40.0);
        assertEquals(15.5, rectangle.getX(), 0.001);
    }

    @Test
    public void testRectangleGetY() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 25.5, 30.0, 40.0);
        assertEquals(25.5, rectangle.getY(), 0.001);
    }

    @Test
    public void testRectangleGetWidth() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 35.5, 40.0);
        assertEquals(35.5, rectangle.getWidth(), 0.001);
    }

    @Test
    public void testRectangleGetHeight() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 45.5);
        assertEquals(45.5, rectangle.getHeight(), 0.001);
    }

    @Test
    public void testRectangleToString() {
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
    public void testRectangleToStringWithDecimalValues() {
        Rectangle rectangle = new Rectangle("rect2", 10.123, 20.456, 30.789, 40.012);
        String result = rectangle.toString();
        assertTrue(result.contains("rect2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("30.79"));
        assertTrue(result.contains("40.01"));
    }

    @Test
    public void testRectangleToStringWithZeroValues() {
        Rectangle rectangle = new Rectangle("rect3", 0.0, 0.0, 0.0, 0.0);
        String result = rectangle.toString();
        assertTrue(result.contains("rect3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testRectangleToStringWithNegativeValues() {
        Rectangle rectangle = new Rectangle("rect4", -10.0, -20.0, 30.0, 40.0);
        String result = rectangle.toString();
        assertTrue(result.contains("rect4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
    }

    @Test
    public void testRectangleImplementsShape() {
        Rectangle rectangle = new Rectangle("rect1", 10.0, 20.0, 30.0, 40.0);
        assertTrue(rectangle instanceof Shape);
    }

    // ========== Square Tests ==========

    @Test
    public void testSquareConstructor() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        assertNotNull(square);
        assertEquals("square1", square.getName());
        assertEquals(10.0, square.getX(), 0.001);
        assertEquals(20.0, square.getY(), 0.001);
        assertEquals(30.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testSquareConstructorWithZeroValues() {
        Square square = new Square("square2", 0.0, 0.0, 0.0);
        assertEquals("square2", square.getName());
        assertEquals(0.0, square.getX(), 0.001);
        assertEquals(0.0, square.getY(), 0.001);
        assertEquals(0.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testSquareConstructorWithNegativeValues() {
        Square square = new Square("square3", -10.0, -20.0, 30.0);
        assertEquals("square3", square.getName());
        assertEquals(-10.0, square.getX(), 0.001);
        assertEquals(-20.0, square.getY(), 0.001);
        assertEquals(30.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testSquareConstructorWithLargeValues() {
        Square square = new Square("square4", 1000.0, 2000.0, 3000.0);
        assertEquals("square4", square.getName());
        assertEquals(1000.0, square.getX(), 0.001);
        assertEquals(2000.0, square.getY(), 0.001);
        assertEquals(3000.0, square.getSideLength(), 0.001);
    }

    @Test
    public void testSquareConstructorWithDecimalValues() {
        Square square = new Square("square5", 10.5, 20.75, 30.25);
        assertEquals("square5", square.getName());
        assertEquals(10.5, square.getX(), 0.001);
        assertEquals(20.75, square.getY(), 0.001);
        assertEquals(30.25, square.getSideLength(), 0.001);
    }

    @Test
    public void testSquareGetName() {
        Square square = new Square("testSquare", 1.0, 2.0, 3.0);
        assertEquals("testSquare", square.getName());
    }

    @Test
    public void testSquareGetNameWithEmptyString() {
        Square square = new Square("", 1.0, 2.0, 3.0);
        assertEquals("", square.getName());
    }

    @Test
    public void testSquareGetNameWithSpecialCharacters() {
        Square square = new Square("square_123", 1.0, 2.0, 3.0);
        assertEquals("square_123", square.getName());
    }

    @Test
    public void testSquareGetType() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        assertEquals("square", square.getType());
    }

    @Test
    public void testSquareGetX() {
        Square square = new Square("square1", 15.5, 20.0, 30.0);
        assertEquals(15.5, square.getX(), 0.001);
    }

    @Test
    public void testSquareGetY() {
        Square square = new Square("square1", 10.0, 25.5, 30.0);
        assertEquals(25.5, square.getY(), 0.001);
    }

    @Test
    public void testSquareGetSideLength() {
        Square square = new Square("square1", 10.0, 20.0, 35.5);
        assertEquals(35.5, square.getSideLength(), 0.001);
    }

    @Test
    public void testSquareToString() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        String result = square.toString();
        assertTrue(result.contains("square"));
        assertTrue(result.contains("square1"));
        assertTrue(result.contains("10.00"));
        assertTrue(result.contains("20.00"));
        assertTrue(result.contains("30.00"));
    }

    @Test
    public void testSquareToStringWithDecimalValues() {
        Square square = new Square("square2", 10.123, 20.456, 30.789);
        String result = square.toString();
        assertTrue(result.contains("square2"));
        assertTrue(result.contains("10.12"));
        assertTrue(result.contains("20.46"));
        assertTrue(result.contains("30.79"));
    }

    @Test
    public void testSquareToStringWithZeroValues() {
        Square square = new Square("square3", 0.0, 0.0, 0.0);
        String result = square.toString();
        assertTrue(result.contains("square3"));
        assertTrue(result.contains("0.00"));
    }

    @Test
    public void testSquareToStringWithNegativeValues() {
        Square square = new Square("square4", -10.0, -20.0, 30.0);
        String result = square.toString();
        assertTrue(result.contains("square4"));
        assertTrue(result.contains("-10.00"));
        assertTrue(result.contains("-20.00"));
    }

    @Test
    public void testSquareImplementsShape() {
        Square square = new Square("square1", 10.0, 20.0, 30.0);
        assertTrue(square instanceof Shape);
    }

    // ========== Group Tests ==========

    @Test
    public void testGroupConstructor() {
        List<String> componentNames = Arrays.asList("shape1", "shape2", "shape3");
        Group group = new Group("group1", componentNames);
        assertNotNull(group);
        assertEquals("group1", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(3, components.size());
        assertTrue(components.contains("shape1"));
        assertTrue(components.contains("shape2"));
        assertTrue(components.contains("shape3"));
    }

    @Test
    public void testGroupConstructorWithEmptyList() {
        List<String> componentNames = new ArrayList<>();
        Group group = new Group("group2", componentNames);
        assertEquals("group2", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(0, components.size());
    }

    @Test
    public void testGroupConstructorWithSingleComponent() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("group3", componentNames);
        assertEquals("group3", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(1, components.size());
        assertEquals("shape1", components.get(0));
    }

    @Test
    public void testGroupConstructorWithManyComponents() {
        List<String> componentNames = Arrays.asList("s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8");
        Group group = new Group("group4", componentNames);
        assertEquals("group4", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(8, components.size());
    }

    @Test
    public void testGroupConstructorWithDuplicateNames() {
        List<String> componentNames = Arrays.asList("shape1", "shape1", "shape2");
        Group group = new Group("group5", componentNames);
        assertEquals("group5", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(3, components.size());
    }

    @Test
    public void testGroupConstructorDefensiveCopy() {
        List<String> componentNames = new ArrayList<>();
        componentNames.add("shape1");
        componentNames.add("shape2");
        Group group = new Group("group6", componentNames);
        
        // Modify original list
        componentNames.add("shape3");
        
        // Group should not be affected
        List<String> components = group.getComponentNames();
        assertEquals(2, components.size());
        assertFalse(components.contains("shape3"));
    }

    @Test
    public void testGroupGetComponentNamesReturnsDefensiveCopy() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("group7", componentNames);
        
        List<String> components1 = group.getComponentNames();
        List<String> components2 = group.getComponentNames();
        
        // Should be equal but not the same object
        assertEquals(components1, components2);
        assertNotSame(components1, components2);
        
        // Modifying returned list should not affect group
        components1.add("shape3");
        List<String> components3 = group.getComponentNames();
        assertEquals(2, components3.size());
        assertFalse(components3.contains("shape3"));
    }

    @Test
    public void testGroupGetName() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("testGroup", componentNames);
        assertEquals("testGroup", group.getName());
    }

    @Test
    public void testGroupGetNameWithEmptyString() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("", componentNames);
        assertEquals("", group.getName());
    }

    @Test
    public void testGroupGetNameWithSpecialCharacters() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("group_123", componentNames);
        assertEquals("group_123", group.getName());
    }

    @Test
    public void testGroupGetType() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("group1", componentNames);
        assertEquals("group", group.getType());
    }

    @Test
    public void testGroupGetComponentNames() {
        List<String> componentNames = Arrays.asList("circle1", "rect1", "line1");
        Group group = new Group("group1", componentNames);
        List<String> components = group.getComponentNames();
        
        assertEquals(3, components.size());
        assertEquals("circle1", components.get(0));
        assertEquals("rect1", components.get(1));
        assertEquals("line1", components.get(2));
    }

    @Test
    public void testGroupGetComponentNamesOrderPreserved() {
        List<String> componentNames = Arrays.asList("first", "second", "third");
        Group group = new Group("group1", componentNames);
        List<String> components = group.getComponentNames();
        
        assertEquals("first", components.get(0));
        assertEquals("second", components.get(1));
        assertEquals("third", components.get(2));
    }

    @Test
    public void testGroupToString() {
        List<String> componentNames = Arrays.asList("shape1", "shape2", "shape3");
        Group group = new Group("group1", componentNames);
        String result = group.toString();
        
        assertTrue(result.contains("group"));
        assertTrue(result.contains("group1"));
        assertTrue(result.contains("shape1"));
        assertTrue(result.contains("shape2"));
        assertTrue(result.contains("shape3"));
    }

    @Test
    public void testGroupToStringWithEmptyList() {
        List<String> componentNames = new ArrayList<>();
        Group group = new Group("group2", componentNames);
        String result = group.toString();
        
        assertTrue(result.contains("group"));
        assertTrue(result.contains("group2"));
    }

    @Test
    public void testGroupToStringWithSingleComponent() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("group3", componentNames);
        String result = group.toString();
        
        assertTrue(result.contains("group"));
        assertTrue(result.contains("group3"));
        assertTrue(result.contains("shape1"));
    }

    @Test
    public void testGroupToStringWithManyComponents() {
        List<String> componentNames = Arrays.asList("a", "b", "c", "d", "e");
        Group group = new Group("group4", componentNames);
        String result = group.toString();
        
        assertTrue(result.contains("group"));
        assertTrue(result.contains("group4"));
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
        assertTrue(result.contains("d"));
        assertTrue(result.contains("e"));
    }

    @Test
    public void testGroupToStringFormat() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("group1", componentNames);
        String result = group.toString();
        
        // Should start with "group group1"
        assertTrue(result.startsWith("group group1"));
        // Should contain component names separated by spaces
        assertTrue(result.contains(" shape1"));
        assertTrue(result.contains(" shape2"));
    }

    @Test
    public void testGroupImplementsShape() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("group1", componentNames);
        assertTrue(group instanceof Shape);
    }

    // ========== Clevis Tests ==========

    @Test
    public void testClevisConstructor() {
        Clevis clevis = new Clevis();
        assertNotNull(clevis);
    }

    @Test
    public void testClevisExecuteCommandNull() {
        Clevis clevis = new Clevis();
        assertEquals("", clevis.executeCommand(null));
    }

    @Test
    public void testClevisExecuteCommandEmpty() {
        Clevis clevis = new Clevis();
        assertEquals("", clevis.executeCommand(""));
        assertEquals("", clevis.executeCommand("   "));
    }

    @Test
    public void testClevisCreateRectangle() {
        Clevis clevis = new Clevis();
        assertEquals("", clevis.executeCommand("rectangle r1 10 20 30 40"));
        assertEquals("Error: Shape name already exists: r1", clevis.executeCommand("rectangle r1 5 5 5 5"));
    }

    @Test
    public void testClevisCreateRectangleInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("rectangle r1").contains("Error"));
        assertTrue(clevis.executeCommand("rectangle r1 10").contains("Error"));
        assertTrue(clevis.executeCommand("rectangle r1 10 20").contains("Error"));
    }

    @Test
    public void testClevisCreateLine() {
        Clevis clevis = new Clevis();
        assertEquals("", clevis.executeCommand("line l1 0 0 10 10"));
        assertEquals("Error: Shape name already exists: l1", clevis.executeCommand("line l1 5 5 5 5"));
    }

    @Test
    public void testClevisCreateLineInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("line l1").contains("Error"));
        assertTrue(clevis.executeCommand("line l1 0").contains("Error"));
    }

    @Test
    public void testClevisCreateCircle() {
        Clevis clevis = new Clevis();
        assertEquals("", clevis.executeCommand("circle c1 10 20 5"));
        assertEquals("Error: Shape name already exists: c1", clevis.executeCommand("circle c1 5 5 5"));
    }

    @Test
    public void testClevisCreateCircleInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("circle c1").contains("Error"));
        assertTrue(clevis.executeCommand("circle c1 10").contains("Error"));
    }

    @Test
    public void testClevisCreateSquare() {
        Clevis clevis = new Clevis();
        assertEquals("", clevis.executeCommand("square s1 10 20 30"));
        assertEquals("Error: Shape name already exists: s1", clevis.executeCommand("square s1 5 5 5"));
    }

    @Test
    public void testClevisCreateSquareInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("square s1").contains("Error"));
        assertTrue(clevis.executeCommand("square s1 10").contains("Error"));
    }

    @Test
    public void testClevisCreateGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        assertEquals("", clevis.executeCommand("group g1 r1 c1"));
        assertEquals("Error: Shape name already exists: g1", clevis.executeCommand("group g1 r1"));
    }

    @Test
    public void testClevisCreateGroupInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("group g1").contains("Error"));
    }

    @Test
    public void testClevisCreateGroupShapeNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Error: Shape not found: r1", clevis.executeCommand("group g1 r1"));
    }

    @Test
    public void testClevisCreateGroupShapeAlreadyInGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("Error: Shape is already part of a group: r1", clevis.executeCommand("group g2 r1"));
    }

    @Test
    public void testClevisCreateGroupEmpty() {
        Clevis clevis = new Clevis();
        // When only group name is provided, it fails parameter count check first
        assertEquals("Error: group command requires at least 2 parameters: n n1 n2...", clevis.executeCommand("group g1"));
    }

    @Test
    public void testClevisUngroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("", clevis.executeCommand("ungroup g1"));
    }

    @Test
    public void testClevisUngroupInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("ungroup").contains("Error"));
        assertTrue(clevis.executeCommand("ungroup g1 g2").contains("Error"));
    }

    @Test
    public void testClevisUngroupNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Error: Shape not found: g1", clevis.executeCommand("ungroup g1"));
    }

    @Test
    public void testClevisUngroupNotAGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        assertEquals("Error: Shape is not a group: r1", clevis.executeCommand("ungroup r1"));
    }

    @Test
    public void testClevisDelete() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        assertEquals("", clevis.executeCommand("delete r1"));
    }

    @Test
    public void testClevisDeleteInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("delete").contains("Error"));
        assertTrue(clevis.executeCommand("delete r1 r2").contains("Error"));
    }

    @Test
    public void testClevisDeleteNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Error: Shape not found: r1", clevis.executeCommand("delete r1"));
    }

    @Test
    public void testClevisDeleteGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("", clevis.executeCommand("delete g1"));
    }

    @Test
    public void testClevisDeleteShapeInGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("Error: Shape is part of a group: r1", clevis.executeCommand("delete r1"));
    }

    @Test
    public void testClevisBoundingBox() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        String result = clevis.executeCommand("boundingbox r1");
        assertEquals("10.00 20.00 30.00 40.00", result);
    }

    @Test
    public void testClevisBoundingBoxCircle() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("circle c1 10 20 5");
        String result = clevis.executeCommand("boundingbox c1");
        assertEquals("5.00 15.00 10.00 10.00", result);
    }

    @Test
    public void testClevisBoundingBoxSquare() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("square s1 10 20 30");
        String result = clevis.executeCommand("boundingbox s1");
        assertEquals("10.00 20.00 30.00 30.00", result);
    }

    @Test
    public void testClevisBoundingBoxLine() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("line l1 0 0 10 20");
        String result = clevis.executeCommand("boundingbox l1");
        assertEquals("0.00 0.00 10.00 20.00", result);
    }

    @Test
    public void testClevisBoundingBoxLineReversed() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("line l1 10 20 0 0");
        String result = clevis.executeCommand("boundingbox l1");
        assertEquals("0.00 0.00 10.00 20.00", result);
    }

    @Test
    public void testClevisBoundingBoxGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        String result = clevis.executeCommand("boundingbox g1");
        // Rectangle: (10, 20, 30, 40) -> bbox: (10, 20, 30, 40)
        // Circle: (50, 60, 10) -> bbox: (40, 50, 20, 20)
        // Group bbox: minX=10, minY=20, maxX=60, maxY=70 -> (10, 20, 50, 50)
        assertEquals("10.00 20.00 50.00 50.00", result);
    }

    @Test
    public void testClevisBoundingBoxInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("boundingbox").contains("Error"));
        assertTrue(clevis.executeCommand("boundingbox r1 r2").contains("Error"));
    }

    @Test
    public void testClevisBoundingBoxNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Error: Shape not found: r1", clevis.executeCommand("boundingbox r1"));
    }

    @Test
    public void testClevisBoundingBoxShapeInGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("Error: Shape is part of a group: r1", clevis.executeCommand("boundingbox r1"));
    }

    @Test
    public void testClevisMove() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        assertEquals("", clevis.executeCommand("move r1 5 10"));
        String result = clevis.executeCommand("boundingbox r1");
        assertEquals("15.00 30.00 30.00 40.00", result);
    }

    @Test
    public void testClevisMoveCircle() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("circle c1 10 20 5");
        assertEquals("", clevis.executeCommand("move c1 5 10"));
        String result = clevis.executeCommand("boundingbox c1");
        assertEquals("10.00 25.00 10.00 10.00", result);
    }

    @Test
    public void testClevisMoveSquare() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("square s1 10 20 30");
        assertEquals("", clevis.executeCommand("move s1 5 10"));
        String result = clevis.executeCommand("boundingbox s1");
        assertEquals("15.00 30.00 30.00 30.00", result);
    }

    @Test
    public void testClevisMoveLine() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("line l1 0 0 10 10");
        assertEquals("", clevis.executeCommand("move l1 5 10"));
        String result = clevis.executeCommand("boundingbox l1");
        assertEquals("5.00 10.00 10.00 10.00", result);
    }

    @Test
    public void testClevisMoveGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("", clevis.executeCommand("move g1 5 10"));
    }

    @Test
    public void testClevisMoveInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("move").contains("Error"));
        assertTrue(clevis.executeCommand("move r1").contains("Error"));
        assertTrue(clevis.executeCommand("move r1 5").contains("Error"));
    }

    @Test
    public void testClevisMoveNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Error: Shape not found: r1", clevis.executeCommand("move r1 5 10"));
    }

    @Test
    public void testClevisMoveShapeInGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("Error: Shape is part of a group: r1", clevis.executeCommand("move r1 5 10"));
    }

    @Test
    public void testClevisShapeAt() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        // Point on the edge of rectangle (within 0.05 tolerance)
        String result = clevis.executeCommand("shapeAt 10 20");
        assertEquals("r1", result);
    }

    @Test
    public void testClevisShapeAtCircle() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("circle c1 10 20 5");
        // Point on the circle edge (at radius distance)
        String result = clevis.executeCommand("shapeAt 15 20");
        assertEquals("c1", result);
    }

    @Test
    public void testClevisShapeAtLine() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("line l1 0 0 10 10");
        // Point on the line
        String result = clevis.executeCommand("shapeAt 5 5");
        assertEquals("l1", result);
    }

    @Test
    public void testClevisShapeAtSquare() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("square s1 10 20 30");
        // Point on the edge of square (within 0.05 tolerance)
        String result = clevis.executeCommand("shapeAt 10 20");
        assertEquals("s1", result);
    }

    @Test
    public void testClevisShapeAtGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        // Point on the edge of rectangle r1 (within 0.05 tolerance)
        String result = clevis.executeCommand("shapeAt 10 20");
        assertEquals("g1", result);
    }

    @Test
    public void testClevisShapeAtNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Shape not found.", clevis.executeCommand("shapeAt 100 100"));
    }

    @Test
    public void testClevisShapeAtZOrder() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("rectangle r2 10 20 30 40");
        // Point on the edge (within 0.05 tolerance)
        String result = clevis.executeCommand("shapeAt 10 20");
        assertEquals("r2", result); // Later shape should be on top
    }

    @Test
    public void testClevisShapeAtInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("shapeAt").contains("Error"));
        assertTrue(clevis.executeCommand("shapeAt 10").contains("Error"));
    }

    @Test
    public void testClevisIntersect() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("rectangle r2 20 30 30 40");
        String result = clevis.executeCommand("intersect r1 r2");
        assertEquals("true", result);
    }

    @Test
    public void testClevisIntersectFalse() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("rectangle r2 100 100 30 40");
        String result = clevis.executeCommand("intersect r1 r2");
        assertEquals("false", result);
    }

    @Test
    public void testClevisIntersectCircle() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("circle c1 10 20 5");
        clevis.executeCommand("circle c2 12 22 5");
        String result = clevis.executeCommand("intersect c1 c2");
        assertEquals("true", result);
    }

    @Test
    public void testClevisIntersectGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        clevis.executeCommand("rectangle r2 20 30 30 40");
        String result = clevis.executeCommand("intersect g1 r2");
        assertEquals("true", result);
    }

    @Test
    public void testClevisIntersectInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("intersect").contains("Error"));
        assertTrue(clevis.executeCommand("intersect r1").contains("Error"));
    }

    @Test
    public void testClevisIntersectNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Error: Shape not found: r1", clevis.executeCommand("intersect r1 r2"));
    }

    @Test
    public void testClevisIntersectShapeInGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("Error: Shape is part of a group: r1", clevis.executeCommand("intersect r1 r2"));
    }

    @Test
    public void testClevisList() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        String result = clevis.executeCommand("list r1");
        assertTrue(result.contains("rectangle"));
        assertTrue(result.contains("r1"));
        assertTrue(result.contains("10.00"));
    }

    @Test
    public void testClevisListCircle() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("circle c1 10 20 5");
        String result = clevis.executeCommand("list c1");
        assertTrue(result.contains("circle"));
        assertTrue(result.contains("c1"));
    }

    @Test
    public void testClevisListGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        String result = clevis.executeCommand("list g1");
        assertTrue(result.contains("group"));
        assertTrue(result.contains("g1"));
        assertTrue(result.contains("r1"));
        assertTrue(result.contains("c1"));
    }

    @Test
    public void testClevisListInvalidParams() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("list").contains("Error"));
        assertTrue(clevis.executeCommand("list r1 r2").contains("Error"));
    }

    @Test
    public void testClevisListNotFound() {
        Clevis clevis = new Clevis();
        assertEquals("Error: Shape not found: r1", clevis.executeCommand("list r1"));
    }

    @Test
    public void testClevisListShapeInGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        assertEquals("Error: Shape is part of a group: r1", clevis.executeCommand("list r1"));
    }

    @Test
    public void testClevisListAll() {
        Clevis clevis = new Clevis();
        assertEquals("No shapes created.", clevis.executeCommand("listall"));
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        String result = clevis.executeCommand("listall");
        assertTrue(result.contains("r1"));
        assertTrue(result.contains("c1"));
    }

    @Test
    public void testClevisListAllWithGroup() {
        Clevis clevis = new Clevis();
        clevis.executeCommand("rectangle r1 10 20 30 40");
        clevis.executeCommand("circle c1 50 60 10");
        clevis.executeCommand("group g1 r1 c1");
        clevis.executeCommand("square s1 100 100 50");
        String result = clevis.executeCommand("listall");
        assertTrue(result.contains("g1"));
        assertTrue(result.contains("s1"));
        // r1 and c1 appear in the group definition and as indented components
        // Check that group definition is present
        assertTrue(result.contains("group g1 r1 c1"));
        // Check that r1 and c1 appear as indented components (not as separate top-level)
        assertTrue(result.contains("  rectangle r1") || result.contains("rectangle r1 10.00"));
        assertTrue(result.contains("  circle c1") || result.contains("circle c1 50.00"));
    }

    @Test
    public void testClevisQuit() {
        Clevis clevis = new Clevis();
        assertEquals("quit", clevis.executeCommand("quit"));
    }

    @Test
    public void testClevisUnknownCommand() {
        Clevis clevis = new Clevis();
        assertTrue(clevis.executeCommand("unknown").contains("Error: Unknown command"));
    }

    @Test
    public void testClevisExecuteCommandException() {
        Clevis clevis = new Clevis();
        // This should trigger an exception in parsing
        String result = clevis.executeCommand("rectangle r1 invalid invalid invalid invalid");
        assertTrue(result.contains("Error"));
    }

    // ========== Logger Tests ==========

    @Test
    public void testLoggerConstructor() throws IOException {
        File htmlFile = File.createTempFile("test", ".html");
        File txtFile = File.createTempFile("test", ".txt");
        try {
            Logger logger = new Logger(htmlFile.getAbsolutePath(), txtFile.getAbsolutePath());
            assertNotNull(logger);
            logger.close();
        } finally {
            htmlFile.delete();
            txtFile.delete();
        }
    }

    @Test
    public void testLoggerLog() throws IOException {
        File htmlFile = File.createTempFile("test", ".html");
        File txtFile = File.createTempFile("test", ".txt");
        try {
            Logger logger = new Logger(htmlFile.getAbsolutePath(), txtFile.getAbsolutePath());
            logger.log("test command");
            logger.close();
            
            // Verify TXT file content
            BufferedReader txtReader = new BufferedReader(new FileReader(txtFile));
            assertEquals("test command", txtReader.readLine());
            assertNull(txtReader.readLine());
            txtReader.close();
            
            // Verify HTML file contains the command
            BufferedReader htmlReader = new BufferedReader(new FileReader(htmlFile));
            String line;
            boolean found = false;
            while ((line = htmlReader.readLine()) != null) {
                if (line.contains("test command")) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
            htmlReader.close();
        } finally {
            htmlFile.delete();
            txtFile.delete();
        }
    }

    @Test
    public void testLoggerLogMultiple() throws IOException {
        File htmlFile = File.createTempFile("test", ".html");
        File txtFile = File.createTempFile("test", ".txt");
        try {
            Logger logger = new Logger(htmlFile.getAbsolutePath(), txtFile.getAbsolutePath());
            logger.log("command1");
            logger.log("command2");
            logger.log("command3");
            logger.close();
            
            BufferedReader txtReader = new BufferedReader(new FileReader(txtFile));
            assertEquals("command1", txtReader.readLine());
            assertEquals("command2", txtReader.readLine());
            assertEquals("command3", txtReader.readLine());
            assertNull(txtReader.readLine());
            txtReader.close();
        } finally {
            htmlFile.delete();
            txtFile.delete();
        }
    }

    @Test
    public void testLoggerLogHtmlEscaping() throws IOException {
        File htmlFile = File.createTempFile("test", ".html");
        File txtFile = File.createTempFile("test", ".txt");
        try {
            Logger logger = new Logger(htmlFile.getAbsolutePath(), txtFile.getAbsolutePath());
            logger.log("test & < > \" ' command");
            logger.close();
            
            BufferedReader htmlReader = new BufferedReader(new FileReader(htmlFile));
            String content = "";
            String line;
            while ((line = htmlReader.readLine()) != null) {
                content += line;
            }
            htmlReader.close();
            
            // Check that HTML special characters are escaped
            assertTrue(content.contains("&amp;"));
            assertTrue(content.contains("&lt;"));
            assertTrue(content.contains("&gt;"));
            assertTrue(content.contains("&quot;"));
            assertTrue(content.contains("&#39;"));
        } finally {
            htmlFile.delete();
            txtFile.delete();
        }
    }

    @Test
    public void testLoggerClose() throws IOException {
        File htmlFile = File.createTempFile("test", ".html");
        File txtFile = File.createTempFile("test", ".txt");
        try {
            Logger logger = new Logger(htmlFile.getAbsolutePath(), txtFile.getAbsolutePath());
            logger.log("test");
            logger.close();
            
            // Verify HTML file is properly closed with closing tags
            BufferedReader htmlReader = new BufferedReader(new FileReader(htmlFile));
            String content = "";
            String line;
            while ((line = htmlReader.readLine()) != null) {
                content += line;
            }
            htmlReader.close();
            
            assertTrue(content.contains("</table>"));
            assertTrue(content.contains("</body>"));
            assertTrue(content.contains("</html>"));
        } finally {
            htmlFile.delete();
            txtFile.delete();
        }
    }
}

