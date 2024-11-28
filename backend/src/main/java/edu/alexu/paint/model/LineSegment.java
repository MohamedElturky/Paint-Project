package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LineSegment extends Shape {

    private double[] points;

    @JsonCreator
    public LineSegment(String id, double x, double y, String stroke,
                       double strokeWidth, double[] points) {
        super(id, x, y, stroke, strokeWidth);
        this.points = points;
    }

    public LineSegment(Shape shape, double[] points) {
        super(shape);
        this.points = points;
    }

    public LineSegment(LineSegment source) {
        super(source);
        this.points = new double[source.points.length];
    }

    public Shape clone() {
        return new LineSegment(this);
    }

    @Override
    public void resize(double... size) {
        this.points = size;
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }
}
