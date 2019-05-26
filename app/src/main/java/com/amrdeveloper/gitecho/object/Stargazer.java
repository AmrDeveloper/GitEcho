package com.amrdeveloper.gitecho.object;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Stargazer {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public Stargazer(String username, String avatarUrl) {
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return username;
    }
}
