import { useState, useEffect, useRef } from "react";
import { Stage, Layer, Transformer } from "react-konva";
import ShapeRenderer from "./ShapeRenderer";
import { changeShapeFillColor } from "./apiService";
import "./App.css";
import {
  resetStage,
  clearStage,
  getShapes,
  addShape,
  deleteShape,
  moveShape,
  resizeShape,
  undoAction,
  redoAction,
  saveShapesAsJSON,
  loadShapesFromJSON,
  saveShapesAsXML,
  loadShapesFromXML,
  changeShapeStrokeColor,
} from "./apiService";

const App = () => {
  const [shapes, setShapes] = useState([]);
  const [selectedId, setSelectedId] = useState(null);
  const [shapeType, setShapeType] = useState("Line");
  const [isDrawing, setIsDrawing] = useState(false);
  const [drawStart, setDrawStart] = useState({ x: 0, y: 0 });
  const [tempShape, setTempShape] = useState(null);
  const [fillColor, setFillColor] = useState("transparent");
  const [strokeColor, setStrokeColor] = useState("black");
  const [selectedShape, setSelectedShape] = useState({ fill: "#ffffff" });
  const stageRef = useRef();

  useEffect(() => {
    const fetchShapes = async () => {
      try {
        const fetchedShapes = await getShapes();
        console.log("Fetched shapes:", fetchedShapes);
        setShapes(fetchedShapes);
      } catch (error) {
        console.error("Error fetching shapes:", error);
      }
    };
    fetchShapes();
  }, []);

  // Calculate radius for circle
  const calculateRadius = (x1, y1, x2, y2) => {
    const width = Math.abs(x2 - x1);
    const height = Math.abs(y2 - y1);
    return Math.min(width, height) / 2;
  };

  // Handle adding a new shape
  const handleAddShape = async (newShape) => {
    try {
      newShape.type = newShape.type.toLowerCase();

      // Fix the line type
      if (newShape.type === "line") {
        newShape.type = "line-segment";
      }

      // Add radius for circle shape
      if (newShape.type === "circle") {
        newShape.radius = calculateRadius(
          newShape.x1,
          newShape.y1,
          newShape.x2,
          newShape.y2
        );
      }

      if (newShape.type === "triangle") {
        newShape.points = [
          newShape.x1,
          newShape.y1,
          newShape.x2,
          newShape.y2,
          newShape.x3,
          newShape.y3,
        ];
        newShape.x = Math.min(newShape.x1, newShape.x2, newShape.x3);
        newShape.y = Math.min(newShape.y1, newShape.y2, newShape.y3);
      } else {
        newShape.x = newShape.x1 || 0;
        newShape.y = newShape.y1 || 0;
      }

      // Handle square shape
      if (newShape.type === "square") {
        const size = Math.abs(newShape.x2 - newShape.x1); // Ensure size calculation
        newShape = {
          ...newShape,
          width: size,
          height: size, // Ensure the height and width are the same for a square
        };
      }

      // Send shape to backend
      await addShape(newShape);

      // Re-fetch all shapes from backend
      const updatedShapes = await getShapes();
      setShapes(updatedShapes);
    } catch (error) {
      console.error("Error adding shape:", error);
    }
  };

  // Handle clear stage
  const handleClearStage = async () => {
    try {
      await clearStage();
      setShapes([]); // Clear local state as well
    } catch (error) {
      console.error("Error clearing stage:", error);
    }
  };

  // Handle reset stage
  const handleResetStage = async () => {
    try {
      await resetStage();
      setShapes([]); // Clear local state as well
    } catch (error) {
      console.error("Error resetting stage:", error);
    }
  };

  // Handle delete shape
  const handleDeleteShape = async (id) => {
    try {
      await deleteShape(id);
      const updatedShapes = await getShapes(); // Re-fetch all shapes from backend
      setShapes(updatedShapes); // Update the state with the latest shapes
    } catch (error) {
      console.error("Error deleting shape:", error);
    }
  };

  // Handle shape drag end
  const handleShapeDragEnd = async (id, x, y) => {
    try {
      await moveShape(id, x, y); // Move shape in backend
      const updatedShapes = await getShapes(); // Re-fetch shapes after move
      setShapes(updatedShapes); // Update state
    } catch (error) {
      console.error("Error moving shape:", error);
    }
  };

  // Handle mouse down to start drawing
  const handleMouseDown = (e) => {
    const { x, y } = e.target.getStage().getPointerPosition();
    setDrawStart({ x, y });
    setIsDrawing(true);
    setTempShape({
      id: Math.random().toString(36).substr(2, 9),
      type: shapeType,
      x1: x,
      y1: y,
      x2: x,
      y2: y,
      width: 0,
      height: 0,
      fill: "transparent",
      stroke: "black",
    });
  };

  // Handle mouse move while drawing
  // Handle mouse move while drawing
  const handleMouseMove = (e) => {
    if (!isDrawing) return;

    const { x, y } = e.target.getStage().getPointerPosition();
    const isSquare = shapeType.toLowerCase() === "square";

    setTempShape((prevShape) => ({
      ...prevShape,
      ...(isSquare
        ? {
            // Calculate the side length based on the distance from the starting point (drawStart)
            sideLength: Math.max(
              Math.abs(x - drawStart.x),
              Math.abs(y - drawStart.y)
            ),
            x2: x, // Keep the x2 to maintain the position for the shape
            y2: y, // Same for y2 to keep the mouse position
            // Ensure the square's top-left corner is determined based on x1 and y1
            x1: drawStart.x,
            y1: drawStart.y,
          }
        : {
            x2: x,
            y2: y,
          }),
    }));
  };

  const calculateCircleCenterAndRadius = (x1, y1, x2, y2) => {
    const centerX = (x1 + x2) / 2;
    const centerY = (y1 + y2) / 2;
    const radius = Math.sqrt(
      Math.pow(x2 - centerX, 2) + Math.pow(y2 - centerY, 2)
    ); // Use distance formula
    return { centerX, centerY, radius };
  };

  const handleMouseUp = async (e) => {
    if (!isDrawing) return;

    const { x, y } = e.target.getStage().getPointerPosition();
    const isSquare = shapeType.toLowerCase() === "square";

    let newShape = {
      id: Math.random().toString(36).substr(2, 9),
      type: shapeType,
      fill: "transparent",
      stroke: "black",
    };

    if (isSquare) {
      const size = Math.abs(x - drawStart.x); // Calculate size for square
      newShape = {
        ...newShape,
        x1: drawStart.x,
        y1: drawStart.y,
        width: size,
        height: size,
        sideLength: size, // Send sideLength to backend
        x: drawStart.x, // Starting X position
        y: drawStart.y, // Starting Y position // Ensure the height and width are the same for a square
      };
    } else if (shapeType.toLowerCase() === "line") {
      newShape = {
        ...newShape,
        points: [drawStart.x, drawStart.y, x, y],
      };
    } else if (shapeType.toLowerCase() === "circle") {
      // Calculate the circle center and radius
      const { centerX, centerY, radius } = calculateCircleCenterAndRadius(
        drawStart.x,
        drawStart.y,
        x,
        y
      );
      newShape = {
        ...newShape,
        x1: centerX, // center of the circle
        y1: centerY, // center of the circle
        radius, // correctly calculated radius
      };
    } else if (shapeType.toLowerCase() === "triangle") {
      // Here, x1, y1, x2, y2, and x3, y3 are the three points defining the triangle
      newShape = {
        ...newShape,
        x1: drawStart.x, // First point (start of the triangle)
        y1: drawStart.y,
        x2: x, // Second point (end of the base of the triangle)
        y2: y,
        x3: (drawStart.x + x) / 2, // Third point (middle, apex of the triangle)
        y3: y - Math.abs(x - drawStart.x), // Adjusted for height of triangle
      };
    } else {
      newShape = {
        ...newShape,
        x1: drawStart.x,
        y1: drawStart.y,
        x2: x,
        y2: y,
        width: Math.abs(x - drawStart.x),
        height: Math.abs(y - drawStart.y),
      };
    }

    // Log the shape to verify radius before adding
    console.log("New Shape with radius:", newShape);

    // Send the shape to the backend
    await handleAddShape(newShape);
    setIsDrawing(false);
    setTempShape(null);
  };

  // Handle undo action
  const handleUndo = async () => {
    try {
      await undoAction();
      const updatedShapes = await getShapes();
      setShapes(updatedShapes); // Update shapes after undo
    } catch (error) {
      console.error("Error undoing action:", error);
    }
  };

  // Handle redo action
  const handleRedo = async () => {
    try {
      await redoAction();
      const updatedShapes = await getShapes();
      setShapes(updatedShapes); // Update shapes after redo
    } catch (error) {
      console.error("Error redoing action:", error);
    }
  };

  // Handle save as JSON
  const handleSaveJSON = async () => {
    try {
      const jsonData = await saveShapesAsJSON();
      const blob = new Blob([JSON.stringify(jsonData)], {
        type: "application/json",
      });
      const url = URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = "shapes.json";
      a.click();
    } catch (error) {
      console.error("Error saving as JSON:", error);
    }
  };

  // Handle load JSON file
  const handleLoadJSON = async (file) => {
    const reader = new FileReader();
    reader.onload = async (e) => {
      try {
        const jsonShapes = JSON.parse(e.target.result);
        await loadShapesFromJSON(jsonShapes);
        const updatedShapes = await getShapes();
        setShapes(updatedShapes); // Update shapes after loading JSON
      } catch (error) {
        console.error("Error loading JSON:", error);
      }
    };
    reader.readAsText(file);
  };

  // Handle save as XML
  const handleSaveXML = async () => {
    try {
      const xmlData = await saveShapesAsXML();
      const blob = new Blob([xmlData], { type: "application/xml" });
      const url = URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = "shapes.xml";
      a.click();
    } catch (error) {
      console.error("Error saving as XML:", error);
    }
  };

  // Handle load XML file
  const handleLoadXML = async (file) => {
    const reader = new FileReader();
    reader.onload = async (e) => {
      try {
        const xmlData = e.target.result;
        await loadShapesFromXML(xmlData);
        const updatedShapes = await getShapes();
        setShapes(updatedShapes); // Update shapes after loading XML
      } catch (error) {
        console.error("Error loading XML:", error);
      }
    };
    reader.readAsText(file);
  };

  const handleFillColorChange = async (color) => {
    try {
      if (selectedId) {
        // Update the backend with the new fill color
        await changeShapeFillColor(selectedId, color);

        // Update the selected shape's fill color in the local state
        setShapes((prevShapes) =>
          prevShapes.map((shape) =>
            shape.id === selectedId ? { ...shape, fill: color } : shape
          )
        );

        // Update the selected shape to reflect the new color
        setSelectedShape((prevShape) => ({
          ...prevShape,
          fill: color,
        }));
      }
    } catch (error) {
      console.error("Error changing fill color:", error);
    }
  };

  const handleStrokeColorChange = async (color) => {
    try {
      if (selectedId) {
        // Find the selected shape from the shapes state
        const selectedShape = shapes.find((shape) => shape.id === selectedId);

        if (selectedShape) {
          // Call the modified API function to update the stroke color
          await changeShapeStrokeColor(
            selectedId,
            color,
            selectedShape,
            setShapes
          ); // Pass setShapes

          // Re-fetch shapes or update state if necessary
          // const updatedShapes = await getShapes(); // Optional if fetching shapes from backend
          // setShapes(updatedShapes); // Update shapes state with the latest from backend
        }
      }
    } catch (error) {
      console.error("Error changing stroke color:", error);
    }
  };

  return (
    <div className="App">
      {/* Toolbar for shape actions */}
      <div className="toolbar">
        {/* Add shape type buttons */}
        {["Line", "Circle", "Rectangle", "Square", "Ellipse", "Triangle"].map(
          (type) => (
            <button key={type} onClick={() => setShapeType(type)}>
              {type}
            </button>
          )
        )}
        <div
          style={{
            width: "100px",
            height: "100px",
            backgroundColor: selectedShape.fill, // This should reflect the current selected shape's color
          }}
        ></div>

        {/* Color pickers */}
        <div>
          <label>Fill Color:</label>
          <input
            type="color"
            value={fillColor}
            onChange={(e) => {
              setFillColor(e.target.value);
              handleFillColorChange(e.target.value);
            }}
          />
        </div>
        <div>
          <label>Stroke Color:</label>
          <input
            type="color"
            value={strokeColor}
            onChange={(e) => {
              setStrokeColor(e.target.value);
              handleStrokeColorChange(e.target.value);
            }}
          />
        </div>
        {/* Other buttons */}
        <button onClick={handleClearStage}>Clear</button>
        <button onClick={handleResetStage}>Reset</button>
        <button onClick={handleUndo}>Undo</button>
        <button onClick={handleRedo}>Redo</button>
        <button
          onClick={() => selectedId && handleDeleteShape(selectedId)}
          disabled={!selectedId}
        >
          Delete
        </button>
        <button onClick={handleSaveJSON}>Save as JSON</button>
        <button onClick={handleSaveXML}>Save as XML</button>
        <input
          type="file"
          onChange={(e) => handleLoadJSON(e.target.files[0])}
        />
        <input type="file" onChange={(e) => handleLoadXML(e.target.files[0])} />
      </div>

      {/* Stage to render shapes */}
      <Stage
        width={window.innerWidth}
        height={window.innerHeight}
        onMouseDown={handleMouseDown}
        onMouseMove={handleMouseMove}
        onMouseUp={handleMouseUp}
        ref={stageRef}
      >
        <Layer>
          {shapes.map((shape) => (
            <ShapeRenderer
              key={shape.id}
              shape={shape}
              onShapeClick={(id) => setSelectedId(id)}
              onShapeDragEnd={handleShapeDragEnd}
            />
          ))}
          {tempShape && <ShapeRenderer shape={tempShape} />}
        </Layer>
      </Stage>
    </div>
  );
};

export default App;
