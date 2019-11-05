package com.sonant.restapi;

import com.google.gson.annotations.SerializedName;

public class Post {
    private Integer id;
    private int userId;
    private String title;

    @SerializedName("body")
    private String text;

    // we are not passing id because it will be auto created by api and we converted type int to Integer
    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

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
