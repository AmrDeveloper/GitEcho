package com.amrdeveloper.gitecho;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("owner")
    private String repoOwner;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String mainLanguage;

    @SerializedName("license")
    private String license;

    @SerializedName("stargazers_count")
    private int starNum;

    @SerializedName("forks")
    private int forkNum;

    @SerializedName("open_issues_count")
    private int openIssuesNum;

    @SerializedName("fork")
    private boolean isForked;

    public Repository(String name, String fullName, String repoOwner,
                      String description, String mainLanguage, String license,
                      int starNum, int forkNum,int openIssuesNum, boolean isForked) {
        this.name = name;
        this.fullName = fullName;
        this.repoOwner = repoOwner;
        this.description = description;
        this.mainLanguage = mainLanguage;
        this.license = license;
        this.starNum = starNum;
        this.forkNum = forkNum;
        this.openIssuesNum = openIssuesNum;
        this.isForked = isForked;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRepoOwner() {
        return repoOwner;
    }

    public String getDescription() {
        return description;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public String getLicense() {
        return license;
    }

    public int getStarNum() {
        return starNum;
    }

    public int getForkNum() {
        return forkNum;
    }

    private int getOpenIssuesNum(){
        return openIssuesNum;
    }

    public boolean isForked() {
        return isForked;
    }
}
