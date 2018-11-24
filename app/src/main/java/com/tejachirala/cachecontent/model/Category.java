package com.tejachirala.cachecontent.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tejachirala on 23/11/18
 */
public class Category {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("photo_count")
    private int photoCount;

    @SerializedName("links")
    private Links links;

    /**
     * No args constructor for use in serialization
     *
     */
    public Category() {
    }

    /**
     *
     * @param id
     * @param title
     * @param photoCount
     * @param links
     */
    public Category(int id, String title, int photoCount, Links links) {
        super();
        this.id = id;
        this.title = title;
        this.photoCount = photoCount;
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
