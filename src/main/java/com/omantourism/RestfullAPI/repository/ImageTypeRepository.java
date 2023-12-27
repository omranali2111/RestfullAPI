package com.omantourism.RestfullAPI.repository;

import com.omantourism.RestfullAPI.model.Image;
import com.omantourism.RestfullAPI.model.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageTypeRepository extends JpaRepository<ImageType, Integer> {
}
