package com.omantourism.RestfullAPI.service;

import com.omantourism.RestfullAPI.model.Image;
import com.omantourism.RestfullAPI.repository.PhotoInfoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Image getImageById(int id) {
        return photoInfoRepository.findById(id).orElse(null);
    }

    public void createImage(Image img) {
        photoInfoRepository.save(img);

    }

    public void updateImage(int id, Image img) {
        Image existingImage = photoInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with ID: " + id));

        existingImage.setDescription(img.getDescription());
        existingImage.setPath(img.getPath());
        photoInfoRepository.save(existingImage);
    }

    public void deleteImage(int id) {
        if (!photoInfoRepository.existsById(id)) {
            throw new EntityNotFoundException("Image not found with ID: " + id);
        }
        photoInfoRepository.deleteById(id);
    }

}