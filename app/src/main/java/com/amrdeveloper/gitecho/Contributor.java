package com.amrdeveloper.gitecho;

import com.google.gson.annotations.SerializedName;

public class Contributor {

    @SerializedName("login")
    private String name;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("contributions")
    private int contributionsNum;

    public Contributor(String name, String avatarUrl, int contributionsNum) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.contributionsNum = contributionsNum;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getcontributionsNum() {
        return contributionsNum;
    }
}
