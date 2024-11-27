package edu.alexu.paint.model;

public class Square extends Shape {

    private double sideLength = 30;

    public Square(String id, String type, double x, double y, String stroke,
                  double strokeWidth, boolean draggable, double sideLength) {
        super(id, type, x, y, stroke, strokeWidth, draggable);
        this.sideLength = sideLength;
    }

    public Square(Square source) {
        super(source);
        sideLength = source.getSideLength();
    }

    public Shape clone() {
        return new Square(this);
    }

    @Override
    public void resize(Double... size) {

    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
}
