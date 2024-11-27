package edu.alexu.paint.model;

public class Ellipse extends Shape {

    private double height;
    private double width;

    public Ellipse(String id, String type, double x, double y, String stroke,
                   double strokeWidth, boolean draggable, double height, double width) {
        super(id, type, x, y, stroke, strokeWidth, draggable);
        this.height = height;
        this.width = width;
    }

    public Ellipse(Ellipse source) {
        super(source);
        this.height = source.height;
        this.width = source.width;
    }

    public Shape clone() {
        return new Ellipse(this);
    }

    @Override
    public void resize(double... size) {
        this.height = size[0];
        this.width = size[1];
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
}
