package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Square extends Shape {

    private double sideLength;

    @JsonCreator
    public Square(String id, double x, double y, String stroke,
                  double strokeWidth, double sideLength) {
        super(id, x, y, stroke, strokeWidth);
        this.sideLength = sideLength;
    }

    public Square(Square source) {
        super(source);
        sideLength = source.getSideLength();
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public Shape clone() {
        return new Square(this);
    }

    @Override
    public void resize(double... size) {
        this.sideLength = size[0];
    }
}
