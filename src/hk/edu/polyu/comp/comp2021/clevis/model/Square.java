package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * A square shape.
 */
public class Square implements Shape {
    private String name;
    private double x;
    private double y;
    private double sideLength;
    
    /**
     * Makes a new square.
     * @param name the square's name
     * @param x x coordinate of the top-left corner
     * @param y y coordinate of the top-left corner
     * @param sideLength the side length
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

