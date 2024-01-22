package com.example.mobil_programlama_final.Model;

public class GalleryModel {
    String label;
    String description;
    String imgUrl;
    String userName;

    public GalleryModel() {
    }

    public GalleryModel(String label, String description, String imgUrl, String userName) {
        this.label = label;
        this.description = description;
        this.imgUrl = imgUrl;
        this.userName = userName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
