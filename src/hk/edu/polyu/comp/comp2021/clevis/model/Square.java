package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Represents a square shape.
 */
public class Square implements Shape {
    private String name;
    private double x;
    private double y;
    private double sideLength;
    
    /**
     * Creates a new square.
     * @param name the unique name of the square
     * @param x the x-coordinate of the top-left corner
     * @param y the y-coordinate of the top-left corner
     * @param sideLength the side length of the square
     */
    public Square(String name, double x, double y, double sideLength) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "square";
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getSideLength() {
        return sideLength;
    }
    
    @Override
    public String toString() {
        return String.format("square %s %.2f %.2f %.2f", name, x, y, sideLength);
    }
}

