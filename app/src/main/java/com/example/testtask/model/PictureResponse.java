package com.example.testtask.model;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PictureResponse {

    @SerializedName("new")
    @Expose
    private boolean newPic;

    @SerializedName("popular")
    @Expose
    private boolean popular;


    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private List<Image> images;

    public boolean isNewPic() {
        return newPic;
    }

    public void setNewPic(boolean newPic) {
        this.newPic = newPic;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
