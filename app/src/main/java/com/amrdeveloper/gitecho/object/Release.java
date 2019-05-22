package com.amrdeveloper.gitecho.object;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Release {

    @SerializedName("name")
    private String name;

    @SerializedName("tag_name")
    private String tagName;

    @SerializedName("author")
    private Owner owner;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("body")
    private String body;

    public Release(String name, String tagName, Owner owner,
                   String createdAt, String body) {
        this.name = name;
        this.tagName = tagName;
        this.owner = owner;
        this.createdAt = createdAt;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public String getTagName() {
        return tagName;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
