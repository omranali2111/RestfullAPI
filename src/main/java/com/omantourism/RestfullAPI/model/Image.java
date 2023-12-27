package com.omantourism.RestfullAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Column(name = "file_path")
    private String path;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_type_id", referencedColumnName = "id")
    private ImageType imageType;




    public Image(int id, String description, String path) {
        this.id = id;
        this.description = description;
        this.path = path;
    }
    public Image(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }
}
