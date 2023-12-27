package com.omantourism.RestfullAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ImageType")
public class ImageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Photo_Type")
    private String PhotoType;

    @OneToOne(mappedBy = "imageType")
    private Image image;

    public ImageType(int id, String photoType) {
        this.id = id;
        PhotoType = photoType;
    }

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
