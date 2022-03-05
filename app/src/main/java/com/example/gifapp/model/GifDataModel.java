package com.example.gifapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GifDataModel {

    @SerializedName("data")
    private List<GifData> data = null;
    @SerializedName("pagination")
    private Pagination pagination;
    @SerializedName("meta")
    private Meta meta;

    public List<GifData> getData() {
        return data;
    }

    public void setData(List<GifData> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}