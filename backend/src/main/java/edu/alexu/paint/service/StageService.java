package edu.alexu.paint.service;

import edu.alexu.paint.dto.ShapeDTO;
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
    private Stack<List<Shape>> historyStack;

    public StageService(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
        this.shapes = new ArrayList<>();
        this.historyStack = new Stack<>();
    }

    public void clear() {
        shapes.clear();
        historyStack.push(shapes);
    }


    public void changeShapeColor(String id, String color) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.setStroke(color);
                return;
            }
        }
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
        return null;
    }

    public void saveAsXML(String filePath) {
    }

    public void saveAsJSON(String filePath) {
    }

    public List<Shape> loadXML(String filePath) {
        return null;
    }

    public List<Shape> loadJSON(String filePath) {
        return null;
    }

    private void saveHistory() {
    }
}