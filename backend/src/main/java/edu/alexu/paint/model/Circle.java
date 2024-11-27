package edu.alexu.paint.model;

public class Circle extends Shape {

    private double radius;

    public Circle(String id, String type, double x, double y, String stroke,
                  double strokeWidth, boolean draggable, double radius) {
        super(id, type, x, y, stroke, strokeWidth, draggable);
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
