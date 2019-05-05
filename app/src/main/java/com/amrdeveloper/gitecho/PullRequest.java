package com.amrdeveloper.gitecho;

public class PullRequest {
    private String title;
    private String state;
    private Creator creator;
    private String createdAt;
    private String pullUrl;
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
