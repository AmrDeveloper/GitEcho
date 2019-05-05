package com.amrdeveloper.gitecho;

public class Repository {

    private String name;
    private String fullName;
    private String repoOwner;
    private String description;
    private String mainLanguage;
    private String license;

    private int starNum;
    private int forkNum;

    private boolean isForked;

    public Repository(String name, String fullName, String repoOwner,
                      String description, String mainLanguage, String license,
                      int starNum, int forkNum, boolean isForked) {
        this.name = name;
        this.fullName = fullName;
        this.repoOwner = repoOwner;
        this.description = description;
        this.mainLanguage = mainLanguage;
        this.license = license;
        this.starNum = starNum;
        this.forkNum = forkNum;
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

    public boolean isForked() {
        return isForked;
    }
}
