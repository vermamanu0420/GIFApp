package com.example.gifapp.model;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("total_count")
    private Integer totalCount;
    @SerializedName("count")
    private Integer count;
    @SerializedName("offset")
    private Integer offset;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}