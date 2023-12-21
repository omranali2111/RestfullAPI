package com.omantourism.RestfullAPI.service;

import com.omantourism.RestfullAPI.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
@Service
public class imageInfoService {
    public ArrayList<Image> images=new ArrayList<>();

    public Image getImageById(String id){
        return images.stream()
                .filter(image -> image.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
    @ResponseBody
    public String createImage(Image img){
        images.add(img);
        return ("Image info added");
    }


    public ResponseEntity<String> updateImage(@PathVariable String id, @RequestBody Image img) {
        Image existingImage = getImageById(id);
        if (existingImage != null) {
            existingImage.setDescription(img.getDescription());
            existingImage.setPath(img.getPath());
            return ResponseEntity.ok("Image info updated successfully.");
        }
        return ResponseEntity.badRequest().body("Image with the given ID not found.");
    }

    public ResponseEntity<String> deleteImage( String id) {
        Image imageToDelete = getImageById(id);
        if (imageToDelete != null) {
            images.remove(imageToDelete);
            return ResponseEntity.ok("Image info removed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Image with the given ID not found.");
        }
    }
}
