package com.amrdeveloper.gitecho.object;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("name")
    private String name;

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("bio")
    private String biography;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("company")
    private String company;

    @SerializedName("public_repos")
    private int reposNumber;

    @SerializedName("public_gists")
    private int gistsNumber;

    @SerializedName("followers")
    private int followersNum;

    @SerializedName("following")
    private int followingNum;

    public Account(String name, String username, String avatarUrl,
                   String biography, String location, String email,
                   String company, int reposNumber, int gistsNumber,
                   int followersNum, int followingNum) {
        this.name = name;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.biography = biography;
        this.location = location;
        this.email = email;
        this.company = company;
        this.reposNumber = reposNumber;
        this.gistsNumber = gistsNumber;
        this.followersNum = followersNum;
        this.followingNum = followingNum;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBiography() {
        return biography;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public int getReposNumber() {
        return reposNumber;
    }

    public int getGistsNumber() {
        return gistsNumber;
    }

    public int getFollowersNum() {
        return followersNum;
    }

    public int getFollowingNum() {
        return followingNum;
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
