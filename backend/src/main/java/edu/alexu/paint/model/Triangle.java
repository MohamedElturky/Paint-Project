package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Triangle extends Shape {

    double[] points;

    @JsonCreator
    public Triangle(ShapeConfig shapeConfig) {
        super(shapeConfig);
        this.points = shapeConfig.points();
    }

    public Triangle(Triangle source){
        super(source);
        this.points = source.getPoints();
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    @Override
    public Shape clone() {
        return new Triangle(this);
    }

    @Override
    public void resize(double... size) {
        this.points = size;
    }
}
