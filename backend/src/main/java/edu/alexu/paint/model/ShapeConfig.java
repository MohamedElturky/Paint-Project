package edu.alexu.paint.model;

public record ShapeConfig(String id, double x, double y, String stroke, double strokeWidth,
                          double sideLength, double width, double height, double radius,
                          double[] points) {}
