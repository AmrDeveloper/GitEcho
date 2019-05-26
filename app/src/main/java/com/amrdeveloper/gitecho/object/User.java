package com.amrdeveloper.gitecho.object;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    private String name;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("type")
    private String userType;

    public User(String name, String avatarUrl,String type) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.userType = type;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Type getUserType(){
        return Type.valueOf(userType.toUpperCase());
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
