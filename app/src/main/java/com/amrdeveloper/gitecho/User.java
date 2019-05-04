package com.amrdeveloper.gitecho;

public class User {
    private String name;
    private String username;
    private String avatarUrl;
    private String location;
    private String email;
    private String company;
    private int reposNumber;
    private int gistsNumber;
    private int followersNum;
    private int followingNum;

    public User(String name, String username, String avatarUrl,
                String location, String email, String company,
                int reposNumber, int gistsNumber, int followersNum,
                int followingNum) {
        this.name = name;
        this.username = username;
        this.avatarUrl = avatarUrl;
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
}
