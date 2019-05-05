package com.amrdeveloper.gitecho;

import com.google.gson.annotations.SerializedName;

public class Issue {

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("state")
    private String state;
    private String creatorName;
    private String creatorAvatarUrl;

    @SerializedName("created_at")
    private String createdAt;

    public Issue(String title, String body, String state,
                 String creatorName, String creatorAvatarUrl, String createdAt) {
        this.title = title;
        this.body = body;
        this.state = state;
        this.creatorName = creatorName;
        this.creatorAvatarUrl = creatorAvatarUrl;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getState() {
        return state;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getCreatorAvatarUrl() {
        return creatorAvatarUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
