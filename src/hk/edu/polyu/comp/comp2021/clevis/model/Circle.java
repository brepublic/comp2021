package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Represents a circle shape.
 */
public class Circle implements Shape {
    private String name;
    private double x;
    private double y;
    private double radius;
    
    /**
     * Creates a new circle.
     * @param name the unique name of the circle
     * @param x the x-coordinate of the center
     * @param y the y-coordinate of the center
     * @param radius the radius of the circle
     */
    public Circle(String name, double x, double y, double radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "circle";
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getRadius() {
        return radius;
    }
    
    @Override
    public String toString() {
        return String.format("circle %s %.2f %.2f %.2f", name, x, y, radius);
    }
}

