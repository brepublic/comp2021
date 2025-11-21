package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for the Group class.
 */
public class GroupTest {

    @Test
    public void testConstructor() {
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
    public void testConstructorWithEmptyList() {
        List<String> componentNames = new ArrayList<>();
        Group group = new Group("group2", componentNames);
        assertEquals("group2", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(0, components.size());
    }

    @Test
    public void testConstructorWithSingleComponent() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("group3", componentNames);
        assertEquals("group3", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(1, components.size());
        assertEquals("shape1", components.get(0));
    }

    @Test
    public void testConstructorWithManyComponents() {
        List<String> componentNames = Arrays.asList("s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8");
        Group group = new Group("group4", componentNames);
        assertEquals("group4", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(8, components.size());
    }

    @Test
    public void testConstructorWithDuplicateNames() {
        List<String> componentNames = Arrays.asList("shape1", "shape1", "shape2");
        Group group = new Group("group5", componentNames);
        assertEquals("group5", group.getName());
        List<String> components = group.getComponentNames();
        assertEquals(3, components.size());
    }

    @Test
    public void testConstructorDefensiveCopy() {
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
    public void testGetComponentNamesReturnsDefensiveCopy() {
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
    public void testGetName() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("testGroup", componentNames);
        assertEquals("testGroup", group.getName());
    }

    @Test
    public void testGetNameWithEmptyString() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("", componentNames);
        assertEquals("", group.getName());
    }

    @Test
    public void testGetNameWithSpecialCharacters() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("group_123", componentNames);
        assertEquals("group_123", group.getName());
    }

    @Test
    public void testGetType() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("group1", componentNames);
        assertEquals("group", group.getType());
    }

    @Test
    public void testGetComponentNames() {
        List<String> componentNames = Arrays.asList("circle1", "rect1", "line1");
        Group group = new Group("group1", componentNames);
        List<String> components = group.getComponentNames();
        
        assertEquals(3, components.size());
        assertEquals("circle1", components.get(0));
        assertEquals("rect1", components.get(1));
        assertEquals("line1", components.get(2));
    }

    @Test
    public void testGetComponentNamesOrderPreserved() {
        List<String> componentNames = Arrays.asList("first", "second", "third");
        Group group = new Group("group1", componentNames);
        List<String> components = group.getComponentNames();
        
        assertEquals("first", components.get(0));
        assertEquals("second", components.get(1));
        assertEquals("third", components.get(2));
    }

    @Test
    public void testToString() {
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
    public void testToStringWithEmptyList() {
        List<String> componentNames = new ArrayList<>();
        Group group = new Group("group2", componentNames);
        String result = group.toString();
        
        assertTrue(result.contains("group"));
        assertTrue(result.contains("group2"));
        // Should not contain any component names
        assertFalse(result.contains("shape"));
    }

    @Test
    public void testToStringWithSingleComponent() {
        List<String> componentNames = Arrays.asList("shape1");
        Group group = new Group("group3", componentNames);
        String result = group.toString();
        
        assertTrue(result.contains("group"));
        assertTrue(result.contains("group3"));
        assertTrue(result.contains("shape1"));
    }

    @Test
    public void testToStringWithManyComponents() {
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
    public void testToStringFormat() {
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
    public void testImplementsShape() {
        List<String> componentNames = Arrays.asList("shape1", "shape2");
        Group group = new Group("group1", componentNames);
        assertTrue(group instanceof Shape);
    }
}

