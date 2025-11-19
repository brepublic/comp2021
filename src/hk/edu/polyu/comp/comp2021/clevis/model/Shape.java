package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Base interface for all shapes in the Clevis system.
 */
public interface Shape {
    /**
     * Gets the name of the shape.
     * @return the name of the shape
     */
    String getName();
    
    /**
     * Gets the type of the shape.
     * @return the type of the shape (e.g., "rectangle", "line", "circle", "square", "group")
     */
    String getType();
}

