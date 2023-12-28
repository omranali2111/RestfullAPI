package com.omantourism.RestfullAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

@Entity
@Table(name = "ImageType")
public class ImageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Photo_Type")
    @Pattern(regexp = "^[a-z|A-Z]+$", message = "Invalid PhotoType format")
    private String PhotoType;
    @JsonIgnore
    @ManyToMany(mappedBy = "imageType")
    private Set<Image> image;



    public ImageType(int id, String photoType) {
        this.id = id;
        PhotoType = photoType;
    }
    public ImageType(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoType() {
        return PhotoType;
    }

    public void setPhotoType(String photoType) {
        PhotoType = photoType;
    }

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

}
