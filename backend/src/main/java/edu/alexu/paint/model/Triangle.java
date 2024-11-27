package edu.alexu.paint.model;

public class Triangle extends Shape{

    private double height;
    private double width;
    private final int sides;

    public Triangle(String id, String type, double x, double y, String stroke,
                    double strokeWidth, boolean draggable, double height,
                    double width, int sides) {
        super(id, type, x, y, stroke, strokeWidth, draggable);
        this.height = height;
        this.width = width;
        this.sides = sides;
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
    public void resize(double... size) {

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
