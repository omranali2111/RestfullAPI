package com.omantourism.RestfullAPI.service;

import com.omantourism.RestfullAPI.model.ImageType;
import com.omantourism.RestfullAPI.repository.ImageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class imageTypeService {
    @Autowired
    private ImageTypeRepository imageTypeRepository;

    public ImageType createImageType(ImageType imageType) {
        return imageTypeRepository.save(imageType);
    }

    public List<ImageType> getAllImageTypes() {
        return imageTypeRepository.findAll();
    }

    public Optional<ImageType> getImageTypeById(int id) {
        return imageTypeRepository.findById(id);
    }

    public Optional<ImageType> updateImageType(int id, ImageType imageTypeDetails) {
        return imageTypeRepository.findById(id).map(imageType -> {
            imageType.setPhotoType(imageTypeDetails.getPhotoType());
            return imageTypeRepository.save(imageType);
        });
    }

    public void deleteImageType(int id) {
        imageTypeRepository.deleteById(id);
    }

}
