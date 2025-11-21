package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * A rectangle shape.
 */
public class Rectangle implements Shape {
    private String name;
    private double x;
    private double y;
    private double width;
    private double height;
    
    /**
     * Makes a new rectangle.
     * @param name the rectangle's name
     * @param x x coordinate of the top-left corner
     * @param y y coordinate of the top-left corner
     * @param width the width
     * @param height the height
     */
    public Rectangle(String name, double x, double y, double width, double height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "rectangle";
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    @Override
    public String toString() {
        return String.format("rectangle %s %.2f %.2f %.2f %.2f", name, x, y, width, height);
    }
}

