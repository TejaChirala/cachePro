package com.tejachirala.cachecontent.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tejachirala on 23/11/18
 */
public class Urls {

    @SerializedName("raw")
    private String raw;

    @SerializedName("full")
    private String full;

    @SerializedName("regular")
    private String regular;

    @SerializedName("small")
    private String small;

    @SerializedName("thumb")
    private String thumb;

    /**
     * No args constructor for use in serialization
     *
     */
    public Urls() {
    }

    /**
     *
     * @param raw
     * @param regular
     * @param full
     * @param thumb
     * @param small
     */
    public Urls(String raw, String full, String regular, String small, String thumb) {
        super();
        this.raw = raw;
        this.full = full;
        this.regular = regular;
        this.small = small;
        this.thumb = thumb;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

}