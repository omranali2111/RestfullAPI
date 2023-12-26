package com.omantourism.RestfullAPI.repository;

import com.omantourism.RestfullAPI.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoInfoRepository extends JpaRepository<Image, Integer> {

}
