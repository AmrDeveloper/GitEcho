package com.amrdeveloper.gitecho;

import com.google.gson.annotations.SerializedName;

public class Issue {

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("state")
    private String state;

    @SerializedName("user")
    private Creator creator;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("number")
    private int number;

    public Issue(String title, String body, String state,
                 Creator creator, String createdAt, int number) {
        this.title = title;
        this.body = body;
        this.state = state;
        this.creator = creator;
        this.createdAt = createdAt;
        this.number = number;
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

    public Creator getIssueUser(){
        return creator;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getIssueNumber(){
        return number;
    }
}
