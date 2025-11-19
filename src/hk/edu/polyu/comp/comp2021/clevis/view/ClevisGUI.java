package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.Logger;
import hk.edu.polyu.comp.comp2021.clevis.controller.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.controller.ClevisListener;
import hk.edu.polyu.comp.comp2021.clevis.model.Circle;
import hk.edu.polyu.comp.comp2021.clevis.model.Line;
import hk.edu.polyu.comp.comp2021.clevis.model.Rectangle;
import hk.edu.polyu.comp.comp2021.clevis.model.Shape;
import hk.edu.polyu.comp.comp2021.clevis.model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Graphical interface that visualizes Clevis drawings and allows interactive command execution.
 */
public class ClevisGUI extends JFrame implements ClevisListener {
    private static final long serialVersionUID = 1L;
    
    private final Clevis clevis;
    private final Logger logger;
    private final DrawingCanvas canvas;
    private final JTextArea outputArea;
    private final JTextField commandField;
    private boolean closed = false;
    
    public ClevisGUI(Clevis clevis, Logger logger) {
        super("Clevis GUI");
        this.clevis = clevis;
        this.logger = logger;
        this.canvas = new DrawingCanvas();
        this.outputArea = new JTextArea(6, 40);
        this.commandField = new JTextField();
        
        configureLayout();
        configureListeners();
        
        clevis.addListener(this);
        canvas.setShapes(clevis.getRenderableShapes());
        
        setSize(1024, 720);
        setLocationRelativeTo(null);
    }
    
    private void configureLayout() {
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        
        JPanel commandPanel = new JPanel(new BorderLayout(8, 0));
        JLabel commandLabel = new JLabel("Command:");
        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(e -> executeCommand());
        commandField.addActionListener(e -> executeCommand());
        
        commandPanel.add(commandLabel, BorderLayout.WEST);
        commandPanel.add(commandField, BorderLayout.CENTER);
        commandPanel.add(executeButton, BorderLayout.EAST);
        
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        JButton fitButton = new JButton("Fit");
        fitButton.addActionListener(e -> canvas.fitToContent());
        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(e -> canvas.zoomBy(1.25));
        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener(e -> canvas.zoomBy(0.8));
        toolbar.add(fitButton);
        toolbar.add(zoomInButton);
        toolbar.add(zoomOutButton);
        
        JPanel drawingWrapper = new JPanel(new BorderLayout());
        drawingWrapper.add(toolbar, BorderLayout.NORTH);
        drawingWrapper.add(canvas, BorderLayout.CENTER);
        
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Output"));
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, drawingWrapper, outputScroll);
        splitPane.setResizeWeight(0.8);
        
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        root.add(commandPanel, BorderLayout.NORTH);
        root.add(splitPane, BorderLayout.CENTER);
        
        setContentPane(root);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void configureListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                shutdown();
            }
        });
    }
    
    private void executeCommand() {
        String command = commandField.getText().trim();
        if (command.isEmpty()) {
            return;
        }
        commandField.setText("");
        appendOutput("> " + command);
        logger.log(command);
        String output = clevis.executeCommand(command);
        if ("quit".equalsIgnoreCase(output)) {
            appendOutput("Application terminated via quit command.");
            dispose();
            return;
        }
        if (!output.isEmpty()) {
            appendOutput(output);
        }
    }
    
    private void appendOutput(String text) {
        outputArea.append(text + System.lineSeparator());
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }
    
    private void shutdown() {
        if (closed) {
            return;
        }
        closed = true;
        clevis.removeListener(this);
        logger.close();
    }
    
    @Override
    public void onShapesUpdated(List<Shape> shapesSnapshot) {
        SwingUtilities.invokeLater(() -> canvas.setShapes(shapesSnapshot));
    }
    
    /**
     * Entry point for launching the GUI mode.
     * @param args command-line arguments (-html <file> -txt <file>)
     */
    public static void main(String[] args) {
        String htmlFilePath = null;
        String txtFilePath = null;
        
        for (int i = 0; i < args.length; i++) {
            if ("-html".equals(args[i]) && i + 1 < args.length) {
                htmlFilePath = args[++i];
            } else if ("-txt".equals(args[i]) && i + 1 < args.length) {
                txtFilePath = args[++i];
            }
        }
        
        if (htmlFilePath == null || txtFilePath == null) {
            System.err.println("Usage: java hk.edu.polyu.comp.comp2021.clevis.view.ClevisGUI -html <html_file> -txt <txt_file>");
            System.exit(1);
        }
        
        final String html = htmlFilePath;
        final String txt = txtFilePath;
        
        SwingUtilities.invokeLater(() -> {
            try {
                Logger logger = new Logger(html, txt);
                Clevis clevis = new Clevis();
                ClevisGUI gui = new ClevisGUI(clevis, logger);
                gui.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to initialize Clevis GUI: " + e.getMessage(),
                        "Clevis GUI",
                        JOptionPane.ERROR_MESSAGE
                );
                System.exit(1);
            }
        });
    }
    
    /**
     * Canvas responsible for rendering shapes and managing zoom/pan interactions.
     */
    private static final class DrawingCanvas extends JPanel {
        private static final double MIN_ZOOM = 0.1;
        private static final double MAX_ZOOM = 25.0;
        private static final double AUTO_FIT_MARGIN = 20.0;
        
        private List<Shape> shapes = new ArrayList<>();
        private double zoom = 1.0;
        private double panX = 0.0;
        private double panY = 0.0;
        private Point dragAnchor;
        private boolean pendingFit = true;
        
        private DrawingCanvas() {
            setBackground(Color.WHITE);
            setOpaque(true);
            setPreferredSize(new Dimension(800, 600));
            MouseAdapter adapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    dragAnchor = e.getPoint();
                }
                
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (dragAnchor == null) {
                        return;
                    }
                    int dx = e.getX() - dragAnchor.x;
                    int dy = e.getY() - dragAnchor.y;
                    panX += dx / zoom;
                    panY += dy / zoom;
                    dragAnchor = e.getPoint();
                    repaint();
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    dragAnchor = null;
                }
                
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    double factor = e.getPreciseWheelRotation() < 0 ? 1.1 : 0.9;
                    zoomAroundPoint(factor, e.getPoint());
                }
            };
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
            addMouseWheelListener(adapter);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            AffineTransform transform = new AffineTransform();
            transform.scale(zoom, zoom);
            transform.translate(panX, panY);
            Graphics2D shapeGraphics = (Graphics2D) g2.create();
            shapeGraphics.transform(transform);
            shapeGraphics.setStroke(new BasicStroke((float) Math.max(0.5, 1.5 / zoom)));
            
            for (Shape shape : shapes) {
                if (shape instanceof Rectangle) {
                    Rectangle rect = (Rectangle) shape;
                    shapeGraphics.draw(new Rectangle2D.Double(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight()));
                } else if (shape instanceof Square) {
                    Square square = (Square) shape;
                    shapeGraphics.draw(new Rectangle2D.Double(square.getX(), square.getY(), square.getSideLength(), square.getSideLength()));
                } else if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    double d = circle.getRadius() * 2;
                    shapeGraphics.draw(new Ellipse2D.Double(circle.getX() - circle.getRadius(), circle.getY() - circle.getRadius(), d, d));
                } else if (shape instanceof Line) {
                    Line line = (Line) shape;
                    shapeGraphics.draw(new Line2D.Double(line.getX1(), line.getY1(), line.getX2(), line.getY2()));
                }
            }
            
            shapeGraphics.dispose();
            
            // Draw shape names in screen space to keep the font size consistent.
            Graphics2D labelGraphics = (Graphics2D) g2.create();
            labelGraphics.setColor(new Color(0x222222));
            for (Shape shape : shapes) {
                Point labelPoint = toScreenCoordinates(shape, transform);
                if (labelPoint != null) {
                    labelGraphics.drawString(shape.getName(), labelPoint.x + 4, labelPoint.y - 4);
                }
            }
            labelGraphics.dispose();
            g2.dispose();
        }
        
        private Point toScreenCoordinates(Shape shape, AffineTransform transform) {
            double x;
            double y;
            if (shape instanceof Rectangle) {
                Rectangle rect = (Rectangle) shape;
                x = rect.getX() + rect.getWidth() / 2.0;
                y = rect.getY() + rect.getHeight() / 2.0;
            } else if (shape instanceof Square) {
                Square square = (Square) shape;
                x = square.getX() + square.getSideLength() / 2.0;
                y = square.getY() + square.getSideLength() / 2.0;
            } else if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                x = circle.getX();
                y = circle.getY();
            } else if (shape instanceof Line) {
                Line line = (Line) shape;
                x = (line.getX1() + line.getX2()) / 2.0;
                y = (line.getY1() + line.getY2()) / 2.0;
            } else {
                return null;
            }
            Point2D transformed = transform.transform(new Point2D.Double(x, y), null);
            return new Point((int) Math.round(transformed.getX()), (int) Math.round(transformed.getY()));
        }
        
        private void zoomAroundPoint(double factor, Point pivot) {
            double newZoom = clamp(zoom * factor, MIN_ZOOM, MAX_ZOOM);
            factor = newZoom / zoom;
            double worldX = pivot.getX() / zoom - panX;
            double worldY = pivot.getY() / zoom - panY;
            zoom = newZoom;
            panX = (pivot.getX() / zoom) - worldX;
            panY = (pivot.getY() / zoom) - worldY;
            repaint();
        }
        
        void zoomBy(double factor) {
            zoomAroundPoint(factor, new Point(getWidth() / 2, getHeight() / 2));
        }
        
        void fitToContent() {
            if (shapes.isEmpty()) {
                pendingFit = false;
                zoom = 1.0;
                panX = panY = 0.0;
                repaint();
                return;
            }
            if (getWidth() <= 0 || getHeight() <= 0) {
                pendingFit = true;
                return;
            }
            
            Bounds bounds = calculateBounds();
            if (!bounds.isValid()) {
                return;
            }
            bounds.expand(AUTO_FIT_MARGIN);
            
            double contentWidth = Math.max(bounds.getWidth(), 1e-3);
            double contentHeight = Math.max(bounds.getHeight(), 1e-3);
            double scaleX = (getWidth()) / contentWidth;
            double scaleY = (getHeight()) / contentHeight;
            zoom = clamp(Math.min(scaleX, scaleY), MIN_ZOOM, MAX_ZOOM);
            
            double centerX = bounds.minX + contentWidth / 2.0;
            double centerY = bounds.minY + contentHeight / 2.0;
            panX = (getWidth() / 2.0) / zoom - centerX;
            panY = (getHeight() / 2.0) / zoom - centerY;
            pendingFit = false;
            repaint();
        }
        
        void setShapes(List<Shape> newShapes) {
            this.shapes = new ArrayList<>(newShapes);
            pendingFit = true;
            fitToContent();
            repaint();
        }
        
        private Bounds calculateBounds() {
            Bounds bounds = new Bounds();
            for (Shape shape : shapes) {
                if (shape instanceof Rectangle) {
                    Rectangle rect = (Rectangle) shape;
                    bounds.include(rect.getX(), rect.getY());
                    bounds.include(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
                } else if (shape instanceof Square) {
                    Square square = (Square) shape;
                    bounds.include(square.getX(), square.getY());
                    bounds.include(square.getX() + square.getSideLength(), square.getY() + square.getSideLength());
                } else if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    bounds.include(circle.getX() - circle.getRadius(), circle.getY() - circle.getRadius());
                    bounds.include(circle.getX() + circle.getRadius(), circle.getY() + circle.getRadius());
                } else if (shape instanceof Line) {
                    Line line = (Line) shape;
                    bounds.include(line.getX1(), line.getY1());
                    bounds.include(line.getX2(), line.getY2());
                }
            }
            return bounds;
        }
        
        private double clamp(double value, double min, double max) {
            return Math.max(min, Math.min(max, value));
        }
        
        @Override
        public void invalidate() {
            super.invalidate();
            if (pendingFit) {
                SwingUtilities.invokeLater(this::fitToContent);
            }
        }
        
        private static final class Bounds {
            private double minX = Double.MAX_VALUE;
            private double minY = Double.MAX_VALUE;
            private double maxX = -Double.MAX_VALUE;
            private double maxY = -Double.MAX_VALUE;
            
            void include(double x, double y) {
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
            
            void expand(double amount) {
                if (!isValid()) {
                    return;
                }
                minX -= amount;
                minY -= amount;
                maxX += amount;
                maxY += amount;
            }
            
            double getWidth() {
                return maxX - minX;
            }
            
            double getHeight() {
                return maxY - minY;
            }
            
            boolean isValid() {
                return minX <= maxX && minY <= maxY;
            }
        }
    }
}

