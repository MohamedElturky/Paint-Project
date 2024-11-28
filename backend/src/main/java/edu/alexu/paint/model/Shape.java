package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"  // Use the 'typed' field to decide the class type
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = Square.class, name = "square"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Ellipse.class, name = "ellipse"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle"),
        @JsonSubTypes.Type(value = LineSegment.class, name = "line-segment")
})
abstract public class Shape implements Cloneable {

    private String id;
    private final String type;
    private double x;
    private double y;
    private String stroke;
    private double strokeWidth;
    private final boolean draggable;

    public Shape(String id, String type, double x, double y, String stroke,
                 double strokeWidth, boolean draggable) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
        this.draggable = draggable;
    }

    public Shape(Shape source) {
        this.id = source.getId();
        this.type = source.getType();
        this.x = source.getX();
        this.y = source.getY();
        this.stroke = source.getStroke();
        this.strokeWidth = source.getStrokeWidth();
        this.draggable = source.isDraggable();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getStroke() {
        return stroke;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void changeColor(String color) {
         this.stroke = color;
    }

    public abstract Shape clone();

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Shape copy() {
        return clone();
    }

    abstract public void resize(double... size);

}
