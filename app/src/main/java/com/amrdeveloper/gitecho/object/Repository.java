package com.amrdeveloper.gitecho.object;

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
    private License license;

    @SerializedName("stargazers_count")
    private int starNum;

    @SerializedName("forks")
    private int forkNum;

    @SerializedName("open_issues_count")
    private int openIssuesNum;

    @SerializedName("fork")
    private boolean isForked;

    @SerializedName("archived")
    private boolean isArchived;

    public Repository(String name, String fullName, String repoOwner,
                      String description, String mainLanguage, License license,
                      int starNum, int forkNum, int openIssuesNum,
                      boolean isForked, boolean isArchived) {
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
        this.isArchived = isArchived;
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

    public License getLicense() {
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

    public boolean isArchived() {
        return isArchived;
    }
}
