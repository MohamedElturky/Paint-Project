package edu.alexu.paint.model;

public class Square extends Shape {

    private double sideLength = 30;

    public Square(String type, String id, boolean draggable) {
        super(type, id, draggable);
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
