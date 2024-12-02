const API_BASE_URL = "http://localhost:8081/api";

// Reset the stage (cannot be undone)
export const resetStage = async () => {
  const response = await fetch(`${API_BASE_URL}/reset`, {
    method: "DELETE",
  });
  if (!response.ok) {
    throw new Error("Failed to reset the stage.");
  }
};

// Clear the stage (can be undone)
export const clearStage = async () => {
  const response = await fetch(`${API_BASE_URL}/clear`, {
    method: "DELETE",
  });
  if (!response.ok) {
    throw new Error("Failed to clear the stage.");
  }
};

// Fetch all shapes
export const getShapes = async () => {
  try {
    const response = await fetch("http://localhost:8081/api/shapes");
    if (response.ok) {
      return await response.json(); // Return the list of shapes
    } else {
      throw new Error("Failed to fetch shapes");
    }
  } catch (error) {
    console.error("Error fetching shapes:", error);
    return [];
  }
};

// Add a new shape
export const addShape = async (shape) => {
  const response = await fetch(`${API_BASE_URL}/add`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(shape),
  });
  if (!response.ok) {
    throw new Error("Failed to add shape.");
  }
};

// Delete a shape
export const deleteShape = async (id) => {
  const response = await fetch(`${API_BASE_URL}/delete?id=${id}`, {
    method: "DELETE",
  });
  if (!response.ok) {
    throw new Error("Failed to delete shape.");
  }
};

export const changeShapeFillColor = async (id, color, shape, setShape) => {
  const response = await fetch(
    `${API_BASE_URL}/fill?id=${id}&fill=${encodeURIComponent(color)}`,
    {
      method: "PUT",
    }
  );
  if (!response.ok) {
    throw new Error("Failed to change shape fill color.");
  }

  // After successfully changing the color on the backend, update the frontend state
  const updatedShape = { ...shape, fillColor: color }; // Assuming `shape` is your shape object in the state
  setShape(updatedShape); // Update the state with the new shape
};

export const changeShapeStrokeColor = async (id, color, shape, setShapes) => {
  const response = await fetch(
    `${API_BASE_URL}/stroke?id=${id}&color=${encodeURIComponent(color)}`,
    {
      method: "PUT",
    }
  );
  if (!response.ok) {
    throw new Error("Failed to change shape stroke color.");
  }

  // After successfully changing the color on the backend, update the frontend state
  const updatedShape = { ...shape, stroke: color }; // Update stroke color
  setShapes((prevShapes) =>
    prevShapes.map(
      (s) => (s.id === id ? updatedShape : s) // Replace the updated shape in the state
    )
  );
};

// Move a shape
export const moveShape = async (id, x, y, shapes, setShapes) => {
  const response = await fetch(`${API_BASE_URL}/move?id=${id}&x=${x}&y=${y}`, {
    method: "PATCH",
  });
  if (!response.ok) {
    throw new Error("Failed to move shape.");
  }

  // Update the local state after the successful move
  setShapes(
    shapes.map((shape) => (shape.id === id ? { ...shape, x, y } : shape))
  );
};

// Copy a shape
export const copyShape = async (id) => {
  const response = await fetch(`${API_BASE_URL}/copy?id=${id}`, {
    method: "POST",
  });
  if (!response.ok) {
    throw new Error("Failed to copy shape.");
  }
};

// Resize a shape
export const resizeShape = async (id, dimensions) => {
  const queryParams = new URLSearchParams(dimensions).toString();
  const response = await fetch(
    `${API_BASE_URL}/resize?id=${id}&${queryParams}`,
    {
      method: "PATCH",
    }
  );
  if (!response.ok) {
    throw new Error("Failed to resize shape.");
  }
};

// Undo the last action
export const undoAction = async () => {
  const response = await fetch(`${API_BASE_URL}/undo`, {
    method: "PUT", // Change to PUT since backend is expecting PUT
    headers: {
      "Content-Type": "application/json", // Ensure the correct header is sent
      Accept: "application/json",
    },
  });
  if (!response.ok) {
    throw new Error("Failed to undo action.");
  }
};

// Redo the last undone action
export const redoAction = async () => {
  const response = await fetch(`${API_BASE_URL}/redo`, {
    method: "PUT", // Change to PUT since backend is expecting PUT
    headers: {
      "Content-Type": "application/json", // Ensure the correct header is sent
      Accept: "application/json",
    },
  });
  if (!response.ok) {
    throw new Error("Failed to redo action.");
  }
};

// Save shapes as JSON
export const saveShapesAsJSON = async () => {
  const response = await fetch(`${API_BASE_URL}/save/json`);
  if (!response.ok) {
    throw new Error("Failed to save shapes as JSON.");
  }
  return response.json();
};

// Load shapes from JSON
export const loadShapesFromJSON = async (shapes) => {
  const response = await fetch(`${API_BASE_URL}/load/json`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(shapes),
  });
  if (!response.ok) {
    throw new Error("Failed to load shapes from JSON.");
  }
};

// Save shapes as XML
export const saveShapesAsXML = async () => {
  const response = await fetch(`${API_BASE_URL}/save/xml`);
  if (!response.ok) {
    throw new Error("Failed to save shapes as XML.");
  }
  return response.text(); // Assuming XML is returned as a string
};

// Load shapes from XML
export const loadShapesFromXML = async (xmlData) => {
  const response = await fetch(`${API_BASE_URL}/load/xml`, {
    method: "POST",
    headers: { "Content-Type": "application/xml" },
    body: xmlData,
  });
  if (!response.ok) {
    throw new Error("Failed to load shapes from XML.");
  }
};
