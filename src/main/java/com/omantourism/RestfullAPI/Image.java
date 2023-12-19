package com.omantourism.RestfullAPI;

public class Image {
    public String id;
    public String description;
    public String path;

    public Image(String id, String description, String path) {
        this.id = id;
        this.description = description;
        this.path = path;
    }

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
