package com.example.gifapp.model;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("downsized_small")
    private ImageDetails downsizedSmall;

    public ImageDetails getDownsizedSmall() {
        return downsizedSmall;
    }

    public void setDownsizedSmall(ImageDetails downsizedSmall) {
        this.downsizedSmall = downsizedSmall;
    }

}