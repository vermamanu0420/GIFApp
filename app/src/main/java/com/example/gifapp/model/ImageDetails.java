package com.example.gifapp.model;

import com.google.gson.annotations.SerializedName;

public class ImageDetails {

    @SerializedName("height")
    private String height;
    @SerializedName("width")
    private String width;
    @SerializedName("mp4_size")
    private String mp4Size;
    @SerializedName("mp4")
    private String mp4;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMp4Size() {
        return mp4Size;
    }

    public void setMp4Size(String mp4Size) {
        this.mp4Size = mp4Size;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

}
