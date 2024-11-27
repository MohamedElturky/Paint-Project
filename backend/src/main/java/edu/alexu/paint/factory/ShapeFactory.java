package edu.alexu.paint.factory;

import edu.alexu.paint.model.Circle;
import edu.alexu.paint.model.Shape;
import edu.alexu.paint.model.Square;

public class ShapeFactory {
    public static Shape getShape(DTO) {
        return switch (DTO.type) {
            case "circle" -> new Circle(DTO.radius, DTO.x, );
            case "square" -> new Square(type, id, draggable);
            default -> null;
        };
    }
}
