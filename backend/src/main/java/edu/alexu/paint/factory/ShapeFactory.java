package edu.alexu.paint.factory;

import edu.alexu.paint.model.Shape;
import edu.alexu.paint.dto.ShapeDTO;
import edu.alexu.paint.model.Circle;
import edu.alexu.paint.model.Square;
import edu.alexu.paint.model.Rectangle;
import edu.alexu.paint.model.Ellipse;
import edu.alexu.paint.model.Triangle;
import edu.alexu.paint.model.LineSegment;
import org.springframework.stereotype.Component;

@Component
public class ShapeFactory {
    public Shape getShape(ShapeDTO shapeDTO) {

        Shape shape = new Shape(shapeDTO.getId(),shapeDTO.getX(),
                          shapeDTO.getY(), shapeDTO.getStroke(),
                          shapeDTO.getStrokeWidth());

        return switch (shapeDTO.getType().toLowerCase()) {
            case "circle" -> new Circle(shape, shapeDTO.getRadius());
            case "square" -> new Square(shape, shapeDTO.getSideLength());
            case "rectangle" -> new Rectangle(shape, shapeDTO.getHeight(), shapeDTO.getWidth());
            case "ellipse" -> new Ellipse(shape, shapeDTO.getHeight(), shapeDTO.getWidth());
            case "triangle" -> new Triangle(shape, shapeDTO.getPoints());
            case "line-segment" -> new LineSegment(shape, shapeDTO.getPoints());
            default -> throw new RuntimeException("Invalid shape type");
        };

    }
}
