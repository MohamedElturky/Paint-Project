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

    private int shapeCount;
    private final ShapeFactory shapeFactory;
    private List<Shape> shapes;
    private final Stack<List<Shape>> backwardStack;
    private final Stack<List<Shape>> forwardStack;

    public StageService(ShapeFactory shapeFactory) {
        shapeCount = 0;
        this.shapeFactory = shapeFactory;
        this.shapes = new ArrayList<>();
        this.backwardStack = new Stack<>();
        this.forwardStack = new Stack<>();
        backwardStack.push(new ArrayList<>(shapes)); // Empty stage
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    private void reset() {
        shapeCount = 0;
        shapes.clear();
        backwardStack.clear();
        forwardStack.clear();
    }

    public void clear() {
        shapes.clear();
        backwardStack.push(new ArrayList<>(shapes));
    }

    public void addShape(ShapeDTO shapeDTO) {
        shapeDTO.setId(String.valueOf(++shapeCount));
        Shape shape = shapeFactory.getShape(shapeDTO);
        shapes.add(shape);
        backwardStack.push(new ArrayList<>(shapes));
    }

    public void deleteShape(String id) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shapes.remove(shape);
                backwardStack.push(new ArrayList<>(shapes));
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void changeShapeColor(String id, String color) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.changeColor(color);
                backwardStack.push(new ArrayList<>(shapes));
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }


    public void moveShape(String id, double x, double y) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.move(x, y);
                backwardStack.push(new ArrayList<>(shapes));
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
                backwardStack.push(new ArrayList<>(shapes));
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void resizeShape(String id, double... size) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.resize(size);
                backwardStack.push(new ArrayList<>(shapes));
                return;
            }
        }
        throw new RuntimeException("Shape not found");
    }

    public void undo() {
        if (backwardStack.size() > 1) {
            forwardStack.push(backwardStack.pop());
            shapes = backwardStack.peek();
        }
    }

    public void redo() {
        if (!forwardStack.isEmpty()) {
            backwardStack.push(forwardStack.pop());
            shapes = backwardStack.peek();
        }
    }

    public List<Shape> save() {
        return this.shapes;
    }

    public void load(List<Shape> shapes) {
        reset();
        this.shapes.addAll(shapes);
    }

}