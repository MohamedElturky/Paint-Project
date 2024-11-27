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

    private final ShapeFactory shapeFactory;
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


    public void addShape(ShapeDTO shapeDTO) {
        Shape shape = shapeFactory.getShape(shapeDTO);
        shapes.add(shape);
        historyStack.push(shapes);
    }

    public void changeShapeColor(String id, String color) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.changeColor(color);
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }


    public void deleteShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shapes.remove(shape);
                historyStack.push(shapes);
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void moveShape(String id, double x, double y) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.move(x, y);
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public Shape copyShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                return shape.copy();
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void resizeShape(String id, double... size) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.resize(size);
            }
        }
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