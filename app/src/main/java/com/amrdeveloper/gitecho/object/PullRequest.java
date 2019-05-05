package com.amrdeveloper.gitecho.object;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("title")
    private String title;

    @SerializedName("state")
    private String state;

    @SerializedName("user")
    private Creator creator;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("url")
    private String pullUrl;

    @SerializedName("number")
    private int number;

    public PullRequest(String title, String state, Creator creator,
                       String createdAt, String pullUrl, int number) {
        this.title = title;
        this.state = state;
        this.creator = creator;
        this.createdAt = createdAt;
        this.pullUrl = pullUrl;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public Creator getCreator() {
        return creator;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPullUrl() {
        return pullUrl;
    }

    public int getNumber() {
        return number;
    }
}
