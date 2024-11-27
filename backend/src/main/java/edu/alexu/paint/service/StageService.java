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
    private final Stack<List<Shape>> backwardStack;
    private final Stack<List<Shape>> forwardStack;

    public StageService(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
        this.shapes = new ArrayList<>();
        this.backwardStack = new Stack<>();
        this.forwardStack = new Stack<>();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void clear() {
        shapes.clear();
        backwardStack.push(shapes);
    }

    public void addShape(ShapeDTO shapeDTO) {
        Shape shape = shapeFactory.getShape(shapeDTO);
        shapes.add(shape);
        backwardStack.push(shapes);
    }

    public void changeShapeColor(String id, String color) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.changeColor(color);
                backwardStack.push(shapes);
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }


    public void deleteShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shapes.remove(shape);
                backwardStack.push(shapes);
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void moveShape(String id, double x, double y) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.move(x, y);
                backwardStack.push(shapes);
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void copyShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shapes.add(shape.copy());
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void resizeShape(String id, double... size) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.resize(size);
                backwardStack.push(shapes);
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void undo() {
        if (!backwardStack.isEmpty()) {
            forwardStack.push(backwardStack.pop());
            shapes = backwardStack.peek();
        }
    }

    public void redo() {
        if (!forwardStack.isEmpty()) {
            backwardStack.push(forwardStack.pop());
            shapes = forwardStack.peek();
        }
    }

    public void save(String filePath, String fileFormat) {
    }

    public void load(String filePath, String fileFormat) {

    }

    private void saveAsXML(String filePath) {
    }

    private void saveAsJSON(String filePath) {
    }

    private List<Shape> loadXML(String filePath) {
        return null;
    }

    private List<Shape> loadJSON(String filePath) {
        return null;
    }

}