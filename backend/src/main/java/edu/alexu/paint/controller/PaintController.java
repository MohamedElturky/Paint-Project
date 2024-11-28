package edu.alexu.paint.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.alexu.paint.model.Shape;
import edu.alexu.paint.dto.ShapeDTO;
import edu.alexu.paint.service.StageService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaintController {

    private final StageService stageService;

    public PaintController(StageService stageService) {
        this.stageService = stageService;
    }

    @DeleteMapping("/clear")
    public List<Shape> clear() {
        stageService.clear();
        return stageService.getShapes();
    }

    @PostMapping("/add")
    public List<Shape> addShape(@RequestBody ShapeDTO shapeDTO) {
        stageService.addShape(shapeDTO);
        return stageService.getShapes();
    }

    @DeleteMapping("/delete")
    public List<Shape> deleteShape(@RequestParam String id) {
        stageService.deleteShape(id);
        return stageService.getShapes();
    }

    @PutMapping("/color")
    public List<Shape> changeShapeColor(@RequestParam String id, @RequestParam String color) {
        stageService.changeShapeColor(id, color);
        return stageService.getShapes();
    }

    @PutMapping("/move")
    public List<Shape> moveShape(@RequestParam String id, @RequestParam double x,
                                 @RequestParam double y) {
        stageService.moveShape(id, x, y);
        return stageService.getShapes();
    }

    @PostMapping("/copy")
    public List<Shape> copyShape(@RequestParam String id) {
        stageService.copyShape(id);
        return stageService.getShapes();
    }

    @PutMapping("/resize")
    public List<Shape> resizeShape(@RequestParam String id, @RequestParam double[] size) {
        stageService.resizeShape(id, size);
        return stageService.getShapes();
    }

    @PutMapping("/undo")
    public List<Shape> undo() {
        stageService.undo();
        return stageService.getShapes();
    }

    @PutMapping("/redo")
    public List<Shape> redo() {
        stageService.redo();
        return stageService.getShapes();
    }

    @GetMapping("/save")
    public List<Shape> saveStage(@RequestParam String fileFormat) {
        return stageService.save(fileFormat);
    }

    @PostMapping("/load")
    public List<Shape> loadStage(@RequestParam String fileFormat, @RequestBody List<Shape> shapes) {
        stageService.load(fileFormat, shapes);
        return stageService.getShapes();
    }
}