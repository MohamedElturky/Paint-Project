package edu.alexu.paint.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Square extends Shape {

    private double sideLength;

    @JsonCreator
    public Square(ShapeConfig shapeConfig) {
        super(shapeConfig);
        this.sideLength = shapeConfig.sideLength();
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
