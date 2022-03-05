package com.example.gifapp.model;

import com.google.gson.annotations.SerializedName;

public class GifData {

    @SerializedName("type")

    private String type;
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("slug")
    private String slug;
    @SerializedName("bitly_gif_url")
    private String bitlyGifUrl;
    @SerializedName("bitly_url")
    private String bitlyUrl;
    @SerializedName("embed_url")
    private String embedUrl;
    @SerializedName("username")
    private String username;
    @SerializedName("source")
    private String source;
    @SerializedName("title")
    private String title;
    @SerializedName("rating")
    private String rating;
    @SerializedName("content_url")
    private String contentUrl;
    @SerializedName("source_tld")
    private String sourceTld;
    @SerializedName("source_post_url")
    private String sourcePostUrl;
    @SerializedName("is_sticker")
    private Integer isSticker;
    @SerializedName("import_datetime")
    private String importDatetime;
    @SerializedName("trending_datetime")
    private String trendingDatetime;
    @SerializedName("images")
    private Images images;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBitlyGifUrl() {
        return bitlyGifUrl;
    }

    public void setBitlyGifUrl(String bitlyGifUrl) {
        this.bitlyGifUrl = bitlyGifUrl;
    }

    public String getBitlyUrl() {
        return bitlyUrl;
    }

    public void setBitlyUrl(String bitlyUrl) {
        this.bitlyUrl = bitlyUrl;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getSourceTld() {
        return sourceTld;
    }

    public void setSourceTld(String sourceTld) {
        this.sourceTld = sourceTld;
    }

    public String getSourcePostUrl() {
        return sourcePostUrl;
    }

    public void setSourcePostUrl(String sourcePostUrl) {
        this.sourcePostUrl = sourcePostUrl;
    }

    public Integer getIsSticker() {
        return isSticker;
    }

    public void setIsSticker(Integer isSticker) {
        this.isSticker = isSticker;
    }

    public String getImportDatetime() {
        return importDatetime;
    }

    public void setImportDatetime(String importDatetime) {
        this.importDatetime = importDatetime;
    }

    public String getTrendingDatetime() {
        return trendingDatetime;
    }

    public void setTrendingDatetime(String trendingDatetime) {
        this.trendingDatetime = trendingDatetime;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

}