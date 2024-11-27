package edu.alexu.paint.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public List<Shape> addShape(@RequestBody ShapeDTO shapeDTO) {
        stageService.addShape(shapeDTO);
        return stageService.getShapes();
    }

    @DeleteMapping("/delete")
    public List<Shape> deleteShape(@RequestParam String id) {
        return stageService.getShapes();
    }

    @PutMapping("/resize")
    public List<Shape> resizeShape(@RequestParam String id, @RequestParam double width,
                                   @RequestParam double height) {
        return stageService.getShapes();
    }

    @PutMapping("/move")
    public List<Shape> moveShape(@RequestParam String id, @RequestParam double x,
                                 @RequestParam double y) {
        return stageService.getShapes();
    }

    @PostMapping("/copy")
    public List<Shape> copyShape(@RequestParam String id) {
        return stageService.getShapes();
    }

    @PostMapping("/undo")
    public List<Shape> undo() {
        return stageService.getShapes();
    }

    @PostMapping("/redo")
    public List<Shape> redo() {
        return stageService.getShapes();
    }

    @PostMapping("/save")
    public void saveStage(@RequestParam String filePath, @RequestParam String fileFormat) {
        stageService.save(filePath, fileFormat);
    }

    @PostMapping("/load")
    public List<Shape> loadStage(@RequestParam String filePath, @RequestParam String fileFormat) {
        stageService.load(filePath, fileFormat);
        return stageService.getShapes();
    }
}