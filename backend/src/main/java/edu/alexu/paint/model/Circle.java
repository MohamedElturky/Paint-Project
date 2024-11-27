package edu.alexu.paint.model;

public class Circle extends Shape {

    private double radius = 30;

    public Circle(String type, String id, boolean draggable) {
        super(type, id, draggable);
    }

    public Circle(Circle source) {
        super(source);
        radius = source.getRadius();
    }

    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public void resize(Double... size) {

    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
