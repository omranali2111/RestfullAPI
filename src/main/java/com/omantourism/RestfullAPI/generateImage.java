package com.omantourism.RestfullAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/images")
public class generateImage {
    ArrayList<Image> images=new ArrayList<>();
    @GetMapping
    public ArrayList<Image> getAllImages(){
        return images;
    }
    @GetMapping("/{id}")
    public Image getImageById(@PathVariable String id){
        return images.stream()
                .filter(image -> image.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
    @PostMapping
    @ResponseBody
    public String createImage(@RequestBody Image img){
       images.add(img);
       return ("Image added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateImage(@PathVariable String id,@RequestBody Image img){
       Image newImage= getImageById(id);
        if (img.getId().equals(id)) {
            img.setDescription(newImage.getDescription());
            img.setPath(newImage.getPath());
            return ResponseEntity.ok("Image updated successfully.");
        }

        return ResponseEntity.badRequest().body("Image with the given ID not found.");
}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        Image imageToDelete = getImageById(id);
        if (imageToDelete != null) {
            images.remove(imageToDelete);
            return ResponseEntity.ok("Image removed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Image with the given ID not found.");
        }
    }

}
