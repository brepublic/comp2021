package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * A line shape.
 */
public class Line implements Shape {
    private String name;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    
    /**
     * Makes a new line.
     * @param name the line's name
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @param x2 x coordinate of the second point
     * @param y2 y coordinate of the second point
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

