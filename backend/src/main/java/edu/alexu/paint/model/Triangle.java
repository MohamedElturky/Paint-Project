package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Triangle extends Shape {

    double[] points;

    @JsonCreator
    public Triangle(String id, double x, double y, String stroke,
                    double strokeWidth, double[] points) {
        super(id, x, y, stroke, strokeWidth);
        this.points = points;
    }

    public Triangle(Shape shape, double[] points) {
        super(shape);
        this.points = points;
    }

    public Triangle(Triangle source){
        super(source);
        this.points = source.getPoints();
    }

    public Shape clone() {
        return new Triangle(this);
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    @Override
    public void resize(double... size) {
        points = size;
    }
}
