package com.omantourism.RestfullAPI.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Column(name = "file_path")
    private String path;


    @ManyToMany
    @JoinTable(
            name = "image_info_image_type_map",
            joinColumns = @JoinColumn(name = "imageinfo_id"),
            inverseJoinColumns = @JoinColumn(name = "imagetype_id")
    )
    private Set<ImageType> imageType;




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


    public Set<ImageType> getImageType() {
        return imageType;
    }

    public void setImageType(Set<ImageType> imageType) {
        this.imageType = imageType;
    }
}
