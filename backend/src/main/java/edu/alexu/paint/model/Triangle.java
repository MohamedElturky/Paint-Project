package edu.alexu.paint.model;

public class Triangle extends Shape {

    double[] points;

    public Triangle(String id, String type, double x, double y, String stroke,
                    double strokeWidth, boolean draggable, double[] points) {
        super(id, type, x, y, stroke, strokeWidth, draggable);
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
