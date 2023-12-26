package com.omantourism.RestfullAPI.controller;

import com.omantourism.RestfullAPI.model.Image;
import com.omantourism.RestfullAPI.service.imageInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class imageInfoController {
    @Autowired
    imageInfoService imageInfoService;;

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageInfoService.getAllImages();
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable int id) {
        Image image = imageInfoService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createImage(@RequestBody Image img) {
        imageInfoService.createImage(img);
        return ResponseEntity.ok("Image created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateImage(@PathVariable int id, @RequestBody Image img) {
        try {
            imageInfoService.updateImage(id, img);
            return ResponseEntity.ok("Image info updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable int id) {
        try {
            imageInfoService.deleteImage(id);
            return ResponseEntity.ok("Image successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
