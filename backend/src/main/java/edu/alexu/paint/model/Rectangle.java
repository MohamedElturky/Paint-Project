package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Rectangle extends Shape {

    private double height;
    private double width;

    @JsonCreator
    public Rectangle(ShapeConfig shapeConfig) {
        super(shapeConfig);
        this.height = shapeConfig.height();
        this.width = shapeConfig.width();
    }

    public Rectangle(Rectangle source) {
        super(source);
        this.height = source.height;
        this.width = source.width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

    @Override
    public void resize(double... size) {
        this.height = size[0];
        this.width = size[1];
    }
}
