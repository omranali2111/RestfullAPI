package com.omantourism.RestfullAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image {

    @Id
    private String id;

    private String description;

    @Column(name = "file_path")
    private String path;

    public Image(String id, String description, String path) {
        this.id = id;
        this.description = description;
        this.path = path;
    }
    public Image(){}

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }
}
