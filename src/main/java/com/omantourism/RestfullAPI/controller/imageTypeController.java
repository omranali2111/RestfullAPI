package com.omantourism.RestfullAPI.controller;

import com.omantourism.RestfullAPI.model.ImageType;
import com.omantourism.RestfullAPI.repository.ImageTypeRepository;
import com.omantourism.RestfullAPI.service.imageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/imageTypes")
public class imageTypeController {
    @Autowired
    private ImageTypeRepository imageTypeRepository;
    @Autowired
    private imageTypeService ImageTypeService;


    @PostMapping
    public ImageType createImageType(@RequestBody ImageType imageType) {
        return ImageTypeService.createImageType(imageType);
    }

    @GetMapping
    public List<ImageType> getAllImageTypes() {
        return ImageTypeService.getAllImageTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageType> getImageTypeById(@PathVariable int id) {
        return ImageTypeService.getImageTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageType> updateImageType(@PathVariable int id, @RequestBody ImageType imageTypeDetails) {
        return ImageTypeService.updateImageType(id, imageTypeDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImageType(@PathVariable int id) {
        try {
            ImageTypeService.deleteImageType(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
