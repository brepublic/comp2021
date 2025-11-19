package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.model.*;

import java.util.*;

/**
 * Main class for the Clevis drawing tool.
 * Manages shapes and executes commands.
 */
public class Clevis {
    private Map<String, Shape> shapes;
    private List<String> shapeOrder; // Maintains Z-order (later shapes have higher Z-index)
    private Map<String, Boolean> groupedShapes; // Tracks which shapes are part of a group
    
    public Clevis() {
        this.shapes = new HashMap<>();
        this.shapeOrder = new ArrayList<>();
        this.groupedShapes = new HashMap<>();
    }
    
    /**
     * Executes a command and returns the output.
     * @param command the command string
     * @return the output string (empty if no output)
     */
    public String executeCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "";
        }
        
        String[] parts = command.trim().split("\\s+");
        if (parts.length == 0) {
            return "";
        }
        
        String cmd = parts[0].toLowerCase();
        
        try {
            switch (cmd) {
                case "rectangle":
                    return createRectangle(parts);
                case "line":
                    return createLine(parts);
                case "circle":
                    return createCircle(parts);
                case "square":
                    return createSquare(parts);
                case "group":
                    return createGroup(parts);
                case "ungroup":
                    return ungroupShape(parts);
                case "delete":
                    return deleteShape(parts);
                case "boundingbox":
                    return getBoundingBox(parts);
                case "move":
                    return moveShape(parts);
                case "shapeat":
                    return findShapeAt(parts);
                case "intersect":
                    return checkIntersect(parts);
                case "list":
                    return listShape(parts);
                case "listall":
                    return listAll();
                case "quit":
                    return "quit";
                default:
                    return "Error: Unknown command: " + cmd;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private String createRectangle(String[] parts) {
        if (parts.length != 6) {
            return "Error: rectangle command requires 5 parameters: n x y w h";
        }
        String name = parts[1];
        if (shapes.containsKey(name)) {
            return "Error: Shape name already exists: " + name;
        }
        double x = Double.parseDouble(parts[2]);
        double y = Double.parseDouble(parts[3]);
        double w = Double.parseDouble(parts[4]);
        double h = Double.parseDouble(parts[5]);
        
        Rectangle rect = new Rectangle(name, x, y, w, h);
        shapes.put(name, rect);
        shapeOrder.add(name);
        return "";
    }
    
    private String createLine(String[] parts) {
        if (parts.length != 6) {
            return "Error: line command requires 5 parameters: n x1 y1 x2 y2";
        }
        String name = parts[1];
        if (shapes.containsKey(name)) {
            return "Error: Shape name already exists: " + name;
        }
        double x1 = Double.parseDouble(parts[2]);
        double y1 = Double.parseDouble(parts[3]);
        double x2 = Double.parseDouble(parts[4]);
        double y2 = Double.parseDouble(parts[5]);
        
        Line line = new Line(name, x1, y1, x2, y2);
        shapes.put(name, line);
        shapeOrder.add(name);
        return "";
    }
    
    private String createCircle(String[] parts) {
        if (parts.length != 5) {
            return "Error: circle command requires 4 parameters: n x y r";
        }
        String name = parts[1];
        if (shapes.containsKey(name)) {
            return "Error: Shape name already exists: " + name;
        }
        double x = Double.parseDouble(parts[2]);
        double y = Double.parseDouble(parts[3]);
        double r = Double.parseDouble(parts[4]);
        
        Circle circle = new Circle(name, x, y, r);
        shapes.put(name, circle);
        shapeOrder.add(name);
        return "";
    }
    
    private String createSquare(String[] parts) {
        if (parts.length != 5) {
            return "Error: square command requires 4 parameters: n x y l";
        }
        String name = parts[1];
        if (shapes.containsKey(name)) {
            return "Error: Shape name already exists: " + name;
        }
        double x = Double.parseDouble(parts[2]);
        double y = Double.parseDouble(parts[3]);
        double l = Double.parseDouble(parts[4]);
        
        Square square = new Square(name, x, y, l);
        shapes.put(name, square);
        shapeOrder.add(name);
        return "";
    }
    
    private String createGroup(String[] parts) {
        if (parts.length < 3) {
            return "Error: group command requires at least 2 parameters: n n1 n2...";
        }
        String groupName = parts[1];
        if (shapes.containsKey(groupName)) {
            return "Error: Shape name already exists: " + groupName;
        }
        
        List<String> componentNames = new ArrayList<>();
        for (int i = 2; i < parts.length; i++) {
            String componentName = parts[i];
            if (!shapes.containsKey(componentName)) {
                return "Error: Shape not found: " + componentName;
            }
            if (groupedShapes.containsKey(componentName) && groupedShapes.get(componentName)) {
                return "Error: Shape is already part of a group: " + componentName;
            }
            componentNames.add(componentName);
        }
        
        if (componentNames.isEmpty()) {
            return "Error: Group must contain at least one shape";
        }
        
        Group group = new Group(groupName, componentNames);
        shapes.put(groupName, group);
        shapeOrder.add(groupName);
        
        // Mark component shapes as grouped
        for (String componentName : componentNames) {
            groupedShapes.put(componentName, true);
        }
        
        return "";
    }
    
    private String ungroupShape(String[] parts) {
        if (parts.length != 2) {
            return "Error: ungroup command requires 1 parameter: n";
        }
        String name = parts[1];
        if (!shapes.containsKey(name)) {
            return "Error: Shape not found: " + name;
        }
        
        Shape shape = shapes.get(name);
        if (!(shape instanceof Group)) {
            return "Error: Shape is not a group: " + name;
        }
        
        Group group = (Group) shape;
        List<String> componentNames = group.getComponentNames();
        
        // Unmark component shapes as grouped
        for (String componentName : componentNames) {
            groupedShapes.remove(componentName);
        }
        
        // Remove the group
        shapes.remove(name);
        shapeOrder.remove(name);
        
        return "";
    }
    
    private String deleteShape(String[] parts) {
        if (parts.length != 2) {
            return "Error: delete command requires 1 parameter: n";
        }
        String name = parts[1];
        if (!shapes.containsKey(name)) {
            return "Error: Shape not found: " + name;
        }
        
        deleteShapeRecursive(name);
        return "";
    }
    
    private void deleteShapeRecursive(String name) {
        Shape shape = shapes.get(name);
        if (shape instanceof Group) {
            Group group = (Group) shape;
            for (String componentName : group.getComponentNames()) {
                deleteShapeRecursive(componentName);
            }
        }
        shapes.remove(name);
        shapeOrder.remove(name);
        groupedShapes.remove(name);
    }
    
    private String getBoundingBox(String[] parts) {
        if (parts.length != 2) {
            return "Error: boundingbox command requires 1 parameter: n";
        }
        String name = parts[1];
        if (!shapes.containsKey(name)) {
            return "Error: Shape not found: " + name;
        }
        
        double[] bbox = calculateBoundingBox(name);
        return String.format("%.2f %.2f %.2f %.2f", bbox[0], bbox[1], bbox[2], bbox[3]);
    }
    
    private double[] calculateBoundingBox(String name) {
        Shape shape = shapes.get(name);
        
        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            return new double[]{rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight()};
        } else if (shape instanceof Square) {
            Square square = (Square) shape;
            return new double[]{square.getX(), square.getY(), square.getSideLength(), square.getSideLength()};
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            double r = circle.getRadius();
            return new double[]{circle.getX() - r, circle.getY() - r, 2 * r, 2 * r};
        } else if (shape instanceof Line) {
            Line line = (Line) shape;
            double minX = Math.min(line.getX1(), line.getX2());
            double maxX = Math.max(line.getX1(), line.getX2());
            double minY = Math.min(line.getY1(), line.getY2());
            double maxY = Math.max(line.getY1(), line.getY2());
            return new double[]{minX, minY, maxX - minX, maxY - minY};
        } else if (shape instanceof Group) {
            Group group = (Group) shape;
            List<String> componentNames = group.getComponentNames();
            if (componentNames.isEmpty()) {
                return new double[]{0, 0, 0, 0};
            }
            
            double minX = Double.MAX_VALUE;
            double minY = Double.MAX_VALUE;
            double maxX = Double.MIN_VALUE;
            double maxY = Double.MIN_VALUE;
            
            for (String componentName : componentNames) {
                double[] bbox = calculateBoundingBox(componentName);
                minX = Math.min(minX, bbox[0]);
                minY = Math.min(minY, bbox[1]);
                maxX = Math.max(maxX, bbox[0] + bbox[2]);
                maxY = Math.max(maxY, bbox[1] + bbox[3]);
            }
            
            return new double[]{minX, minY, maxX - minX, maxY - minY};
        }
        
        return new double[]{0, 0, 0, 0};
    }
    
    private String moveShape(String[] parts) {
        if (parts.length != 4) {
            return "Error: move command requires 3 parameters: n dx dy";
        }
        String name = parts[1];
        if (!shapes.containsKey(name)) {
            return "Error: Shape not found: " + name;
        }
        double dx = Double.parseDouble(parts[2]);
        double dy = Double.parseDouble(parts[3]);
        
        moveShapeRecursive(name, dx, dy);
        return "";
    }
    
    private void moveShapeRecursive(String name, double dx, double dy) {
        Shape shape = shapes.get(name);
        
        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            shapes.put(name, new Rectangle(rect.getName(), rect.getX() + dx, rect.getY() + dy, 
                                          rect.getWidth(), rect.getHeight()));
        } else if (shape instanceof Square) {
            Square square = (Square) shape;
            shapes.put(name, new Square(square.getName(), square.getX() + dx, square.getY() + dy, 
                                       square.getSideLength()));
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            shapes.put(name, new Circle(circle.getName(), circle.getX() + dx, circle.getY() + dy, 
                                       circle.getRadius()));
        } else if (shape instanceof Line) {
            Line line = (Line) shape;
            shapes.put(name, new Line(line.getName(), line.getX1() + dx, line.getY1() + dy, 
                                     line.getX2() + dx, line.getY2() + dy));
        } else if (shape instanceof Group) {
            Group group = (Group) shape;
            for (String componentName : group.getComponentNames()) {
                moveShapeRecursive(componentName, dx, dy);
            }
        }
    }
    
    private String findShapeAt(String[] parts) {
        if (parts.length != 3) {
            return "Error: shapeAt command requires 2 parameters: x y";
        }
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        
        // Search in reverse order (highest Z-index first)
        for (int i = shapeOrder.size() - 1; i >= 0; i--) {
            String name = shapeOrder.get(i);
            if (coversPoint(name, x, y)) {
                return name;
            }
        }
        
        return "Shape not found.";
    }
    
    private boolean coversPoint(String name, double x, double y) {
        Shape shape = shapes.get(name);
        
        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            return distanceToRectangle(x, y, rect) < 0.05;
        } else if (shape instanceof Square) {
            Square square = (Square) shape;
            return distanceToRectangle(x, y, square.getX(), square.getY(), 
                                      square.getSideLength(), square.getSideLength()) < 0.05;
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            double dist = Math.abs(Math.sqrt(Math.pow(x - circle.getX(), 2) + 
                                            Math.pow(y - circle.getY(), 2)) - circle.getRadius());
            return dist < 0.05;
        } else if (shape instanceof Line) {
            Line line = (Line) shape;
            return distanceToLineSegment(x, y, line.getX1(), line.getY1(), 
                                        line.getX2(), line.getY2()) < 0.05;
        } else if (shape instanceof Group) {
            Group group = (Group) shape;
            for (String componentName : group.getComponentNames()) {
                if (coversPoint(componentName, x, y)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private double distanceToRectangle(double x, double y, Rectangle rect) {
        return distanceToRectangle(x, y, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
    
    private double distanceToRectangle(double x, double y, double rectX, double rectY, 
                                      double width, double height) {
        double right = rectX + width;
        double bottom = rectY + height;
        
        // Check if point is inside rectangle (distance is 0)
        if (x >= rectX && x <= right && y >= rectY && y <= bottom) {
            // Distance to nearest edge
            double distToLeft = x - rectX;
            double distToRight = right - x;
            double distToTop = y - rectY;
            double distToBottom = bottom - y;
            return Math.min(Math.min(distToLeft, distToRight), Math.min(distToTop, distToBottom));
        }
        
        // Point is outside, calculate distance to nearest edge
        double dx = Math.max(rectX - x, Math.max(0, x - right));
        double dy = Math.max(rectY - y, Math.max(0, y - bottom));
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    private double distanceToLineSegment(double x, double y, double x1, double y1, double x2, double y2) {
        double A = x - x1;
        double B = y - y1;
        double C = x2 - x1;
        double D = y2 - y1;
        
        double dot = A * C + B * D;
        double lenSq = C * C + D * D;
        double param = lenSq != 0 ? dot / lenSq : -1;
        
        double xx, yy;
        if (param < 0) {
            xx = x1;
            yy = y1;
        } else if (param > 1) {
            xx = x2;
            yy = y2;
        } else {
            xx = x1 + param * C;
            yy = y1 + param * D;
        }
        
        double dx = x - xx;
        double dy = y - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    private String checkIntersect(String[] parts) {
        if (parts.length != 3) {
            return "Error: intersect command requires 2 parameters: n1 n2";
        }
        String name1 = parts[1];
        String name2 = parts[2];
        
        if (!shapes.containsKey(name1)) {
            return "Error: Shape not found: " + name1;
        }
        if (!shapes.containsKey(name2)) {
            return "Error: Shape not found: " + name2;
        }
        
        double[] bbox1 = calculateBoundingBox(name1);
        double[] bbox2 = calculateBoundingBox(name2);
        
        boolean intersect = boundingBoxesIntersect(bbox1, bbox2);
        return intersect ? "true" : "false";
    }
    
    private boolean boundingBoxesIntersect(double[] bbox1, double[] bbox2) {
        double x1 = bbox1[0];
        double y1 = bbox1[1];
        double w1 = bbox1[2];
        double h1 = bbox1[3];
        double x2 = bbox2[0];
        double y2 = bbox2[1];
        double w2 = bbox2[2];
        double h2 = bbox2[3];
        
        // Check if bounding boxes share any internal points
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }
    
    private String listShape(String[] parts) {
        if (parts.length != 2) {
            return "Error: list command requires 1 parameter: n";
        }
        String name = parts[1];
        if (!shapes.containsKey(name)) {
            return "Error: Shape not found: " + name;
        }
        
        return listShapeRecursive(name, 0);
    }
    
    private String listShapeRecursive(String name, int indent) {
        Shape shape = shapes.get(name);
        StringBuilder sb = new StringBuilder();
        String indentStr = "  ".repeat(indent);
        
        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            sb.append(indentStr).append("rectangle ").append(name)
              .append(String.format(" %.2f %.2f %.2f %.2f", rect.getX(), rect.getY(), 
                                    rect.getWidth(), rect.getHeight()));
        } else if (shape instanceof Square) {
            Square square = (Square) shape;
            sb.append(indentStr).append("square ").append(name)
              .append(String.format(" %.2f %.2f %.2f", square.getX(), square.getY(), 
                                    square.getSideLength()));
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            sb.append(indentStr).append("circle ").append(name)
              .append(String.format(" %.2f %.2f %.2f", circle.getX(), circle.getY(), 
                                    circle.getRadius()));
        } else if (shape instanceof Line) {
            Line line = (Line) shape;
            sb.append(indentStr).append("line ").append(name)
              .append(String.format(" %.2f %.2f %.2f %.2f", line.getX1(), line.getY1(), 
                                    line.getX2(), line.getY2()));
        } else if (shape instanceof Group) {
            Group group = (Group) shape;
            sb.append(indentStr).append("group ").append(name);
            for (String componentName : group.getComponentNames()) {
                sb.append(" ").append(componentName);
            }
            sb.append("\n");
            for (String componentName : group.getComponentNames()) {
                sb.append(listShapeRecursive(componentName, indent + 1)).append("\n");
            }
            // Remove trailing newline
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
                sb.setLength(sb.length() - 1);
            }
        }
        
        return sb.toString();
    }
    
    private String listAll() {
        StringBuilder sb = new StringBuilder();
        // List in reverse order (highest Z-index first)
        for (int i = shapeOrder.size() - 1; i >= 0; i--) {
            String name = shapeOrder.get(i);
            // Only list top-level shapes (not shapes that are part of a group)
            if (!groupedShapes.containsKey(name) || !groupedShapes.get(name)) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(listShapeRecursive(name, 0));
            }
        }
        return (sb.length() == 0) ? "No shapes created." : sb.toString();
    }
}
