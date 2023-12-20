package com.omantourism.RestfullAPI.controller;

import com.omantourism.RestfullAPI.model.Image;
import com.omantourism.RestfullAPI.service.imageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/images")
public class imageInfoController {
    @Autowired
    imageInfoService imagesServic;

    @GetMapping
    public ArrayList<Image> getAllImages() {
        return imagesServic.images;
    }

    @GetMapping("/{id}")
    public Image getImageById(@PathVariable String id) {
        return imagesServic.getImageById(id);

    }

    @PostMapping

    public String createImage(@RequestBody Image img) {

        return imagesServic.createImage(img);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateImage(@PathVariable String id, @RequestBody Image img) {
        return imagesServic.updateImage(id,img);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable String id) {

        return imagesServic.deleteImage(id);

    }
}
