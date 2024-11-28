package edu.alexu.paint.controller;

import edu.alexu.paint.model.Shape;
import edu.alexu.paint.service.StageService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public List<Shape> addShape(@RequestBody Shape shape) {
        stageService.addShape(shape);
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

    @GetMapping("/save/json")
    public List<Shape> saveAsJSON() {
        return stageService.save();
    }

    @PostMapping("/load/json")
    public List<Shape> loadFromJSON(@RequestBody List<Shape> shapes) {
        stageService.load(shapes);
        return stageService.getShapes();
    }

    @GetMapping(value = "/save/xml", produces = "application/xml")
    public List<Shape> saveAsXML() {
        return stageService.getShapes();
    }

    @PostMapping(value = "/load/xml", consumes = "application/xml")
    public List<Shape> loadFromXML(@RequestBody List<Shape> shapes) {
        stageService.load(shapes);
        return stageService.getShapes();
    }
}