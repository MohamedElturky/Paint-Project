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


    @DeleteMapping("/reset")
    public void reset() {
        stageService.reset();
    }

    @DeleteMapping("/clear")
    public void clear() {
        stageService.clear();
    }

    @GetMapping("/shapes")
    public List<Shape> getShapes() {
        return stageService.getShapes();
    }

    @PostMapping("/add")
    public void addShape(@RequestBody Shape shape) {
        stageService.addShape(shape);
    }

    @DeleteMapping("/delete")
    public void deleteShape(@RequestParam String id) {
        stageService.deleteShape(id);
    }

    @PutMapping("/color")
    public void changeShapeColor(@RequestParam String id, @RequestParam String color) {
        stageService.changeStrokeColor(id, color);
    }

    @PutMapping("/fill")
    public void changeFill(@RequestParam String id, @RequestParam String fill) {
        stageService.changeFill(id, fill);
    }

    @PutMapping("/move")
    public void moveShape(@RequestParam String id, @RequestParam double x,
                                 @RequestParam double y) {
        stageService.moveShape(id, x, y);
    }

    @PostMapping("/copy")
    public void copyShape(@RequestParam String id) {
        stageService.copyShape(id);
    }

    @PutMapping("/resize")
    public void resizeShape(@RequestParam String id, @RequestParam double[] size) {
        stageService.resizeShape(id, size);
    }

    @PutMapping("/undo")
    public void undo() {
        stageService.undo();
    }

    @PutMapping("/redo")
    public void redo() {
        stageService.redo();
    }

    @GetMapping("/save/json")
    public List<Shape> saveAsJSON() {
        return stageService.save();
    }

    @PostMapping("/load/json")
    public void loadFromJSON(@RequestBody List<Shape> shapes) {
        stageService.load(shapes);
    }

    @GetMapping(value = "/save/xml", produces = "application/xml")
    public List<Shape> saveAsXML() {
        return stageService.getShapes();
    }

    @PostMapping(value = "/load/xml", consumes = "application/xml")
    public void loadFromXML(@RequestBody List<Shape> shapes) {
        stageService.load(shapes);
    }
}