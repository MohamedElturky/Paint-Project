package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @Type(value = Circle.class, name = "circle"),
        @Type(value = Square.class, name = "square"),
        @Type(value = Rectangle.class, name = "rectangle"),
        @Type(value = Ellipse.class, name = "ellipse"),
        @Type(value = Triangle.class, name = "triangle"),
        @Type(value = LineSegment.class, name = "line-segment")
})
public class Shape implements Cloneable {

    private String id;
    private double x;
    private double y;
    private String stroke;
    private double strokeWidth;

    public Shape(String id, double x, double y, String stroke,
                 double strokeWidth) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
    }

    public Shape(Shape source) {
        this.id = source.getId();
        this.x = source.getX();
        this.y = source.getY();
        this.stroke = source.getStroke();
        this.strokeWidth = source.getStrokeWidth();
    }

    public String getId() {
        return id;
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

    public Shape clone() {
        return new Shape(this);
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Shape copy() {
        return clone();
    }

    public void resize(double... size) {}

}
