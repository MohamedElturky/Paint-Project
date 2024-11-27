package edu.alexu.paint.service;

import edu.alexu.paint.model.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;

import edu.alexu.paint.factory.ShapeFactory;

@Service
public class StageService {

    private ShapeFactory shapeFactory;
    private List<Shape> shapes;
    private String backgroundColor;
    private double width;
    private double height;
    private Stack<List<Shape>> historyStack;

    public StageService(ShapeFactory shapeFactory, String backgroundColor, double width, double height) {
        this.shapeFactory = shapeFactory;
        this.shapes = new ArrayList<>();
        this.backgroundColor = backgroundColor;
        this.width = width;
        this.height = height;
        this.historyStack = new Stack<>();
    }

    public void clear() {
    }

    public void resize(double width, double height) {
    }

    public void setBackgroundColor(String color) {
        this.backgroundColor = color;
    }


    public void changeShapeColor(String color) {
    }


    public void addShape(ShapeDTO shapeDTO) {
    }

    public void deleteShape(String id) {
    }

    public void moveShape(String id, double x, double y) {
    }

    public Shape copyShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                return shape.clone();
            }
        }
        throw new RuntimeException("Invalid shape id");
    }

    public void resizeShape(String id, double... size) {
    }

    public void undo() {
    }

    public void redo() {
    }

    public void save(String filePath, String fileFormat) {
    }

    public List<Shape> load(String filePath, String fileFormat) {
    }

    public void saveAsXML(String filePath) {
    }

    public void saveAsJSON(String filePath) {
    }

    public List<Shape> loadXML(String filePath) {
    }

    public List<Shape> loadJSON(String filePath) {
    }

    private void saveHistory() {
    }
}
