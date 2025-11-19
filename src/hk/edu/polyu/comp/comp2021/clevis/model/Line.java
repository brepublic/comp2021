package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Represents a line segment shape.
 */
public class Line implements Shape {
    private String name;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    
    /**
     * Creates a new line segment.
     * @param name the unique name of the line
     * @param x1 the x-coordinate of the first endpoint
     * @param y1 the y-coordinate of the first endpoint
     * @param x2 the x-coordinate of the second endpoint
     * @param y2 the y-coordinate of the second endpoint
     */
    public Line(String name, double x1, double y1, double x2, double y2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "line";
    }
    
    public double getX1() {
        return x1;
    }
    
    public double getY1() {
        return y1;
    }
    
    public double getX2() {
        return x2;
    }
    
    public double getY2() {
        return y2;
    }
    
    @Override
    public String toString() {
        return String.format("line %s %.2f %.2f %.2f %.2f", name, x1, y1, x2, y2);
    }
}

