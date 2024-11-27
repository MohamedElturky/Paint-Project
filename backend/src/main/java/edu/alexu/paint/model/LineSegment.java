package edu.alexu.paint.model;

public class LineSegment extends Shape{

    private double[] points = new double[4];

    public LineSegment(String type, String id, boolean draggable) {
        super(type, id, draggable);
    }

    public LineSegment(LineSegment source){
        super(source);
        this.points = new double[source.points.length];
    }

    public Shape clone() {
        return new LineSegment(this);
    }

    @Override
    public void resize(Double... size) {

    }

    public double[] getPoints(){
        return points;
    }

    public void setPoints(double[] points){
        this.points = points;
    }
}
