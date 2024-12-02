import PropTypes from "prop-types";
import { Line, Ellipse, Circle, Rect } from "react-konva";

const ShapeRenderer = ({
  shape,
  onShapeClick,
  onShapeDragEnd,
  handleAddShape,
  selected,
}) => {
  // Add visual indication for selected shapes
  const isSelectedStyle = selected ? { stroke: "blue", strokeWidth: 2 } : {};

  // Calculate the radiusA for circles
  const calculateRadius = (x1, y1, x2, y2) => {
    const width = Math.abs(x2 - x1);
    const height = Math.abs(y2 - y1);
    return Math.min(width, height) / 2;
  };

  // Handle dragging the shape
  const handleDragEnd = (e) => {
    const { x, y } = e.target.attrs;
    onShapeDragEnd(shape.id, x, y);
  };

  // Add radius to circle before sending it to the backend
  if (shape.type === "circle" && shape.x1 && shape.y1 && shape.x2 && shape.y2) {
    shape.radius = calculateRadius(shape.x1, shape.y1, shape.x2, shape.y2); // Calculate and add radius
  }

  // Optional: Add shape to backend when it’s created or updated (if you have handleAddShape)
  if (handleAddShape && shape.type === "circle") {
    // Ensure that the shape object contains the calculated radius
    shape.radius = calculateRadius(shape.x1, shape.y1, shape.x2, shape.y2); // Add radius to shape
    handleAddShape(shape); // This function should take the shape with calculated radius and send it to the backend
  }

  // Handle rendering triangles correctly
  if (shape.type === "triangle") {
    const points = shape.points || [];
    if (points.length === 6) {
      return (
        <Line
          points={points} // Ensure this is passed directly from backend
          closed
          draggable
          onDragEnd={handleDragEnd}
          onClick={() => onShapeClick(shape.id)}
          fill={shape.fill || "transparent"}
          stroke={shape.stroke || "black"}
          strokeWidth={shape.strokeWidth || 1}
        />
      );
    }
  }

  // Render shapes based on type
  switch (shape.type) {
    case "triangle": {
      const points = [
        shape.x1,
        shape.y1, // Top vertex
        shape.x2,
        shape.y1, // Bottom right
        (shape.x1 + shape.x2) / 2,
        shape.y2, // Bottom center
        { ...isSelectedStyle }, // Add selected styles
      ];
      return (
        <Line
          points={points}
          closed
          draggable
          onDragEnd={handleDragEnd}
          onClick={() => onShapeClick(shape.id)}
          stroke={shape.stroke || "black"}
          strokeWidth={shape.strokeWidth || 1}
          {...isSelectedStyle} // Add selected styles
        />
      );
    }

    case "ellipse": {
      // Ensure ellipse uses `x`, `y`, `width`, and `height`
      return (
        <Ellipse
          x={shape.x || 0}
          y={shape.y || 0}
          radiusX={(shape.width || 0) / 2} // Ensure radius is half of width
          radiusY={(shape.height || 0) / 2} // Ensure radius is half of height
          draggable
          onDragEnd={handleDragEnd}
          onClick={() => onShapeClick(shape.id)}
          fill={shape.fill || "transparent"}
          stroke={shape.stroke || "black"}
          strokeWidth={shape.strokeWidth || 1}
          {...isSelectedStyle} // Add selected styles
        />
      );
    }

    case "circle": {
      return (
        <Circle
          key={shape.id}
          x={shape.x1}
          y={shape.y1}
          radius={shape.radius}
          fill={shape.fill}
          stroke={shape.stroke}
          onClick={() => onShapeClick(shape.id)}
          draggable
          onDragEnd={(e) =>
            onShapeDragEnd(shape.id, e.target.x(), e.target.y())
          }
          {...isSelectedStyle} // Add selected styles
        />
      );
    }

    case "rectangle": {
      // Ensure rectangle uses `x`, `y`, `width`, and `height`
      return (
        <Rect
          x={shape.x || 0}
          y={shape.y || 0}
          width={shape.width || 100} // Default width if missing
          height={shape.height || 100} // Default height if missing
          draggable
          onDragEnd={handleDragEnd}
          onClick={() => onShapeClick(shape.id)}
          fill={shape.fill || "transparent"}
          stroke={shape.stroke || "black"}
          strokeWidth={shape.strokeWidth || 1}
          {...isSelectedStyle} // Add selected styles
        />
      );
    }
    case "line-segment": {
      return (
        <Line
          points={shape.points || [0, 0, 100, 100]} // Default points for line
          x={shape.x || 0}
          y={shape.y || 0}
          draggable
          onDragEnd={handleDragEnd}
          onClick={() => onShapeClick(shape.id)}
          stroke={shape.stroke || "black"}
          strokeWidth={shape.strokeWidth || 1}
          {...isSelectedStyle} // Add selected styles
        />
      );
    }
    case "square": {
      const size = shape.sideLength || Math.abs(shape.x2 - shape.x1);
      return (
        <Rect
          x={shape.x1}
          y={shape.y1}
          width={size}
          height={size}
          draggable
          onDragEnd={handleDragEnd}
          onClick={() => onShapeClick(shape.id)}
          fill={shape.fill || "transparent"}
          stroke={shape.stroke || "black"}
          strokeWidth={shape.strokeWidth || 1}
          {...isSelectedStyle} // Add selected styles
        />
      );
    }

    default:
      return null; // Do not render unknown shapes
  }
};

ShapeRenderer.propTypes = {
  shape: PropTypes.shape({
    id: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    x: PropTypes.number,
    y: PropTypes.number,
    x1: PropTypes.number,
    y1: PropTypes.number,
    x2: PropTypes.number,
    y2: PropTypes.number,
    width: PropTypes.number,
    height: PropTypes.number,
    points: PropTypes.arrayOf(PropTypes.number),
    stroke: PropTypes.string,
    strokeWidth: PropTypes.number,
    radius: PropTypes.number, // Ensure radius is included
  }).isRequired,
  onShapeClick: PropTypes.func.isRequired,
  onShapeDragEnd: PropTypes.func.isRequired,
  handleAddShape: PropTypes.func, // Function to handle adding the shape (sending to backend)
};

export default ShapeRenderer;
