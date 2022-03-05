package com.example.gifapp.model;

import com.google.gson.annotations.SerializedName;

public class ImageDetails {

    @SerializedName("height")
    private String height;
    @SerializedName("width")
    private String width;
    @SerializedName("size")
    private String size;
    @SerializedName("url")
    private String url;

    public String getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }

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


}
