package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Circle extends Shape {

    private double radius;

    @JsonCreator
    public Circle(String id, double x, double y, String stroke,
            double strokeWidth, double radius) {
        super(id, x, y, stroke, strokeWidth);
        this.radius = radius;
    }

    public Circle(Shape shape, double radius) {
        super(shape);
        this.radius = radius;
    }

    public Circle(Circle source) {
        super(source);
        radius = source.getRadius();
    }

    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public void resize(double... size) {
        radius = size[0];
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
