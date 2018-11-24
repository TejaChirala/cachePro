package com.tejachirala.cachecontent.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tejachirala on 23/11/18
 */
public class Account {

    @SerializedName("id")
    private String id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("color")
    private String color;

    @SerializedName("likes")
    private int likes;

    @SerializedName("liked_by_user")
    private boolean likedByUser;

    @SerializedName("user")
    private User user;

    @SerializedName("current_user_collections")
    private List<Object> currentUserCollections = null;

    @SerializedName("urls")
    private Urls urls;

    @SerializedName("categories")
    private List<Category> categories = null;

    @SerializedName("links")
    private Links links;

    /**
     * No args constructor for use in serialization
     *
     */
    public Account() {
    }

    /**
     *
     * @param id
     * @param currentUserCollections
     * @param height
     * @param color
     * @param urls
     * @param createdAt
     * @param likes
     * @param width
     * @param links
     * @param categories
     * @param likedByUser
     * @param user
     */
    public Account(String id, String createdAt, int width, int height, String color,
                   int likes, boolean likedByUser, User user,
                   List<Object> currentUserCollections, Urls urls,
                   List<Category> categories, Links links) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.width = width;
        this.height = height;
        this.color = color;
        this.likes = likes;
        this.likedByUser = likedByUser;
        this.user = user;
        this.currentUserCollections = currentUserCollections;
        this.urls = urls;
        this.categories = categories;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Object> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<Object> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
