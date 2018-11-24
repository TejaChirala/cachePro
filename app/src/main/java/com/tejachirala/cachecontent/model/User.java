package com.tejachirala.cachecontent.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tejachirala on 23/11/18
 */
public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image")
    private ProfileImage profileImage;

    @SerializedName("links")
    private Links links;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param id
     * @param username
     * @param profileImage
     * @param name
     * @param links
     */
    public User(String id, String username, String name, ProfileImage profileImage, Links links) {
        super();
        this.id = id;
        this.username = username;
        this.name = name;
        this.profileImage = profileImage;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
