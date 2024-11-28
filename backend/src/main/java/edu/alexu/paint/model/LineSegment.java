package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LineSegment extends Shape {

    private double[] points;

    @JsonCreator
    public LineSegment(ShapeConfig shapeConfig) {
        super(shapeConfig);
        this.points = shapeConfig.points();
    }

    public LineSegment(LineSegment source) {
        super(source);
        this.points = new double[source.points.length];
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    @Override
    public Shape clone() {
        return new LineSegment(this);
    }

    @Override
    public void resize(double... size) {
        this.points = size;
    }
}
