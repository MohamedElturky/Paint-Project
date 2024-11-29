package edu.alexu.paint.service;

import edu.alexu.paint.model.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;

@Service
public class StageService {

    private int shapeCount;
    private List<Shape> shapes;
    private final Stack<List<Shape>> backwardStack;
    private final Stack<List<Shape>> forwardStack;

    public StageService() {
        shapeCount = 0;
        this.shapes = new ArrayList<>();
        this.backwardStack = new Stack<>();
        this.forwardStack = new Stack<>();
        backwardStack.push(new ArrayList<>(shapes)); // Empty stage
    }

    public void reset() {
        shapeCount = 0;
        shapes.clear();
        backwardStack.clear();
        forwardStack.clear();
        backwardStack.push(new ArrayList<>());
    }

    public void clear() {
        shapes.clear();
        recordAction();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        shape.setId(String.valueOf(++shapeCount));
        shapes.add(shape);
        recordAction();
    }

    public void deleteShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shapes.remove(shape);
                recordAction();
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void changeShapeColor(String id, String color) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.changeColor(color);
                recordAction();
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }


    public void moveShape(String id, double x, double y) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.move(x, y);
                recordAction();
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void copyShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                Shape s = shape.copy();
                s.setId(String.valueOf(++shapeCount));
                shapes.add(s);
                recordAction();
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void resizeShape(String id, double... size) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.resize(size);
                recordAction();
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void undo() {
        if (backwardStack.size() > 1) {
            forwardStack.push(backwardStack.pop());
            this.shapes = cloneShapes(backwardStack.peek());
        }
    }

    public void redo() {
        if (!forwardStack.isEmpty()) {
            backwardStack.push(forwardStack.pop());
            this.shapes = cloneShapes(backwardStack.peek());
        }
    }

    public List<Shape> save() {
        return this.shapes;
    }

    public void load(List<Shape> shapes) {
        reset();
        this.shapes.addAll(shapes);
    }

    private void recordAction() {
        forwardStack.clear();
        backwardStack.push(cloneShapes(this.shapes));
    }

    private List<Shape> cloneShapes(List<Shape> shapes) {
        List<Shape> clone = new ArrayList<>();
        for (Shape shape : shapes) {
            clone.add(shape.copy());
        }
        return clone;
    }
}