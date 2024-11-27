package edu.alexu.paint.model;

public class Triangle extends Shape{

    private double height;
    private double width;
    private int sides;

    public Triangle(String type, String id, boolean draggable) {
        super(type, id, draggable);
    }

    public Triangle(Triangle source){
        super(source);
        this.height = source.height;
        this.width = source.width;
        this.sides = source.sides;
    }

    public Shape clone() {
        return new Triangle(this);
    }

    @Override
    public void resize(Double... size) {

    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    public int getSides(){
        return sides;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }
}
