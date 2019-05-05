package com.amrdeveloper.gitecho;

public class Contributor {

    private String name;
    private String avatarUrl;
    private int contributNum;

    public Contributor(String name, String avatarUrl, int contributNum) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.contributNum = contributNum;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getContributNum() {
        return contributNum;
    }
}
