package com.amrdeveloper.gitecho.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoriesList {

    @SerializedName("items")
    private List<Repository> repositoryList;

    public RepositoriesList(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public List<Repository> getRepositoryList() {
        return repositoryList;
    }
}
