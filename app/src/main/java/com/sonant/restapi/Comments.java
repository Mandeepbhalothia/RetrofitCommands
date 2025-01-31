package com.sonant.restapi;

import com.google.gson.annotations.SerializedName;

public class Comments {
    private int id;
    private int userId;
    private String title;

    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
