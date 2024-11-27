package edu.alexu.paint.model;

public class Rectangle extends Shape{

    private double height;
    private double width;

    public Rectangle(String id, String type, double x, double y, String stroke,
                     double strokeWidth, boolean draggable, double height, double width) {
        super(id, type, x, y, stroke, strokeWidth, draggable);
        this.height = height;
        this.width = width;
    }

    public Rectangle(Rectangle source){
        super(source);
        this.height = source.height;
        this.width = source.width;
    }

    public Shape clone() {
        return new Rectangle(this);
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

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }
}
