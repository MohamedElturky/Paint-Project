package edu.alexu.paint.model;

public class Ellipse extends Shape{

    private double height;
    private double width;

    public Ellipse(String type, String id, boolean draggable) {
        super(type, id, draggable);
    }

    public Ellipse(Ellipse source){
        super(source);
        this.height = source.height;
        this.width = source.width;
    }

    public Shape clone() {
        return new Ellipse(this);
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

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }
}