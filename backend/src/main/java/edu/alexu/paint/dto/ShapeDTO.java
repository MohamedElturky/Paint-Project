package edu.alexu.paint.dto;

public class ShapeDTO {

    private String type;
    private String id;
    private double x;
    private double y;
    private String stroke;
    private double strokeWidth;
    private boolean draggable;
    private double height;
    private double width;
    private double sideLength;
    private double radius;
    private int sides;
    private double[] points;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
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

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getRadius() {
        return radius;
    }

    public int getSides() {
        return sides;
    }

    public double[] getPoints() {
        return points;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }
}
