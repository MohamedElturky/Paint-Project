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
        @Type(value = Circle.class, name = ShapeType.CIRCLE),
        @Type(value = Square.class, name = ShapeType.SQUARE),
        @Type(value = Rectangle.class, name = ShapeType.RECTANGLE),
        @Type(value = Ellipse.class, name = ShapeType.ELLIPSE),
        @Type(value = Triangle.class, name = ShapeType.TRIANGLE),
        @Type(value = LineSegment.class, name = ShapeType.LINE_SEGMENT)
})
public abstract class Shape implements Cloneable {

    private String id;
    private double x;
    private double y;
    private String stroke;
    private double strokeWidth;
    private String fill;

    public Shape(ShapeConfig shapeConfig) {
        this.id = shapeConfig.id();
        this.x = shapeConfig.x();
        this.y = shapeConfig.y();
        this.stroke = shapeConfig.stroke();
        this.strokeWidth = shapeConfig.strokeWidth();
        this.fill = shapeConfig.fill();
    }

    public Shape(Shape source) {
        this.id = source.getId();
        this.x = source.getX();
        this.y = source.getY();
        this.stroke = source.getStroke();
        this.strokeWidth = source.getStrokeWidth();
        this.fill = source.getFill();
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

    public String getFill() {
        return fill;
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

    public void changeStrokeColor(String color) {
         this.stroke = color;
    }

    public void changeFill(String fill) {
        this.fill = fill;
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Shape copy() {
        return this.clone();
    }

    public abstract void resize(double... size);

    public abstract Shape clone();
}
