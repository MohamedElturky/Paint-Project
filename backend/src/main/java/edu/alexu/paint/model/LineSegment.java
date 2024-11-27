package edu.alexu.paint.model;

public class LineSegment extends Shape{

    private double[] points = new double[4];

    public LineSegment(String id, String type, double x, double y, String stroke,
                       double strokeWidth, boolean draggable, double[] points) {
        super(id, type, x, y, stroke, strokeWidth, draggable);
        this.points = points;
    }

    public LineSegment(LineSegment source){
        super(source);
        this.points = new double[source.points.length];
    }

    public Shape clone() {
        return new LineSegment(this);
    }

    @Override
    public void resize(double... size) {

    }

    public double[] getPoints(){
        return points;
    }

    public void setPoints(double[] points){
        this.points = points;
    }
}
