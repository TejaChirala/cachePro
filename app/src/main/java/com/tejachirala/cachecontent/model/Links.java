package com.tejachirala.cachecontent.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tejachirala on 23/11/18
 */
public class Links {

    @SerializedName("self")
    private String self;

    @SerializedName("html")
    private String html;

    @SerializedName("photos")
    private String photos;

    @SerializedName("likes")
    private String likes;

    @SerializedName("download")
    private String download;

    /**
     * No args constructor for use in serialization
     *
     */
    public Links() {
    }

    /**
     *
     * @param photos
     * @param likes
     * @param html
     * @param self
     */
    public Links(String self, String html, String photos, String likes, String download) {
        super();
        this.self = self;
        this.html = html;
        this.photos = photos;
        this.likes = likes;
        this.download = download;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

}
