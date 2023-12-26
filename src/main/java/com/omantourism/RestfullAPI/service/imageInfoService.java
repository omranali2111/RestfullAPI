package com.omantourism.RestfullAPI.service;

import com.omantourism.RestfullAPI.model.Image;
import com.omantourism.RestfullAPI.repository.PhotoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class imageInfoService {

    @Autowired
    private PhotoInfoRepository photoInfoRepository;

    public List<Image> getAllImages() {
        return photoInfoRepository.findAll();
    }


    public Image getImageById(String id) {
        return photoInfoRepository.findById(id).orElse(null);
    }

    public String createImage(Image img) {
        photoInfoRepository.save(img);
        return "Image info added";
    }

    public ResponseEntity<String> updateImage(String id, Image img) {
        if (photoInfoRepository.existsById(id)) {
            img.setId(id);
            photoInfoRepository.save(img);
            return ResponseEntity.ok("Image info updated successfully.");
        }
        return ResponseEntity.badRequest().body("Image with the given ID not found.");
    }

    public ResponseEntity<String> deleteImage(String id) {
        if (photoInfoRepository.existsById(id)) {
            photoInfoRepository.deleteById(id);
            return ResponseEntity.ok("Image info removed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Image with the given ID not found.");
        }
    }
}