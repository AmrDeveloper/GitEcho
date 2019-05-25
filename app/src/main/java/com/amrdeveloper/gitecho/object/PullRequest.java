package com.amrdeveloper.gitecho.object;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    @SerializedName("merged_at")
    private String mergedAt;

    @SerializedName("url")
    private String pullUrl;

    @SerializedName("number")
    private int number;

    public static final String STATE_OPEN = "open";
    public static final String STATE_CLOSE = "close";
    public static final String STATE_MERGE = "merge";

    public PullRequest(String title, String state, Creator creator,
                       String createdAt, String pullUrl, int number) {
        this.title = title;
        this.state = state;
        this.creator = creator;
        this.createdAt = createdAt;
        this.mergedAt = null;
        this.pullUrl = pullUrl;
        this.number = number;
    }

    public PullRequest(String title, String state, Creator creator,
                       String createdAt, String mergedAt, String pullUrl,
                       int number) {
        this.title = title;
        this.state = state;
        this.creator = creator;
        this.createdAt = createdAt;
        this.mergedAt = mergedAt;
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

    public String getMergetAt() {
        return mergedAt;
    }

    public String getPullUrl() {
        return pullUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getPullRequestType() {
        if (state.equals(STATE_OPEN)) {
            return STATE_OPEN;
        }
        return (mergedAt == null) ? STATE_CLOSE : STATE_MERGE;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return pullUrl;
    }
}
