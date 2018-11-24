package com.tejachirala.cachecontent.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tejachirala on 23/11/18
 */
public class ProfileImage {

    @SerializedName("small")
    private String small;

    @SerializedName("medium")
    private String medium;

    @SerializedName("large")
    private String large;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProfileImage() {
    }

    /**
     *
     * @param small
     * @param large
     * @param medium
     */
    public ProfileImage(String small, String medium, String large) {
        super();
        this.small = small;
        this.medium = medium;
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

}
