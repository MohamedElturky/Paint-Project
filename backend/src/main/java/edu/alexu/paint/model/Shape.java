package edu.alexu.paint.model;

abstract public class Shape implements Cloneable {

    private final String id;
    private final String type;
    private double x;
    private double y;
    private String stroke;
    private double strokeWidth;
    private final boolean draggable;

    public Shape(String id, String type, boolean draggable) {
        this.id = id;
        this.type = type;
        this.draggable = draggable;
    }

    public Shape(Shape source) {
        this.id = source.getId();
        this.type = source.getType();
        this.x = source.getX();
        this.y = source.getY();
        this.stroke = source.getStroke();
        this.strokeWidth = source.getStrokeWidth();
        this.draggable = source.isDraggable();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getStroke() {
        return stroke;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void changeColor(String color) {
        this.stroke = color;
    }

    public abstract Shape clone();

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Shape copy() {
        return clone();
    }

    abstract public void resize(Double... size);

}
