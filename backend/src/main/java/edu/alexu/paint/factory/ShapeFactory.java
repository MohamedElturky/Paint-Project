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

import java.util.Locale;

@Component
public class ShapeFactory {
    public Shape getShape(ShapeDTO shapeDTO) {
        return switch (shapeDTO.getType().toLowerCase()) {
            case "circle" ->
                    new Circle(shapeDTO.getId(), shapeDTO.getType(),shapeDTO.getX(),
                    shapeDTO.getY(), shapeDTO.getStroke(), shapeDTO.getStrokeWidth(),
                    shapeDTO.isDraggable(), shapeDTO.getRadius());
            case "square" ->
                    new Square(shapeDTO.getId(), shapeDTO.getType(),shapeDTO.getX(),
                    shapeDTO.getY(), shapeDTO.getStroke(), shapeDTO.getStrokeWidth(),
                    shapeDTO.isDraggable(), shapeDTO.getSideLength());
            case "rectangle" ->
                    new Rectangle(shapeDTO.getId(), shapeDTO.getType(),shapeDTO.getX(),
                    shapeDTO.getY(), shapeDTO.getStroke(), shapeDTO.getStrokeWidth(),
                    shapeDTO.isDraggable(), shapeDTO.getHeight(), shapeDTO.getWidth());
            case "ellipse" ->
                    new Ellipse(shapeDTO.getId(), shapeDTO.getType(),shapeDTO.getX(),
                            shapeDTO.getY(), shapeDTO.getStroke(), shapeDTO.getStrokeWidth(),
                            shapeDTO.isDraggable(), shapeDTO.getHeight(), shapeDTO.getWidth());
            case "triangle" ->
                    new Triangle(shapeDTO.getId(), shapeDTO.getType(),shapeDTO.getX(),
                            shapeDTO.getY(), shapeDTO.getStroke(), shapeDTO.getStrokeWidth(),
                            shapeDTO.isDraggable(), shapeDTO.getPoints());
            case "line-segment" ->
                    new LineSegment(shapeDTO.getId(), shapeDTO.getType(),shapeDTO.getX(),
                            shapeDTO.getY(), shapeDTO.getStroke(), shapeDTO.getStrokeWidth(),
                            shapeDTO.isDraggable(), shapeDTO.getPoints());
            default -> throw new RuntimeException("Invalid shape type");
        };
    }
}
