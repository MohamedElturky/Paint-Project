package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Circle extends Shape {

    private double radius;

    @JsonCreator
    public Circle(ShapeConfig shapeConfig) {
        super(shapeConfig);
        this.radius = shapeConfig.radius();
    }

    public Circle(Circle source) {
        super(source);
        radius = source.getRadius();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public void resize(double... size) {
        radius = size[0];
    }
}
