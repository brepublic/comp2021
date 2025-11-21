package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Base interface for all shapes.
 */
public interface Shape {
    /**
     * Gets the shape's name.
     * @return the shape's name
     */
    String getName();
    
    /**
     * Gets the shape's type.
     * @return the shape's type (like "rectangle", "line", "circle", "square", or "group")
     */
    String getType();
}

