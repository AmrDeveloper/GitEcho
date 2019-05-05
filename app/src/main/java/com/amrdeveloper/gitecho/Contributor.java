package com.amrdeveloper.gitecho;

public class Contributor {

    private String name;
    private String avatarUrl;
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
