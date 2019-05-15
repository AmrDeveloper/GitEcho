package com.amrdeveloper.gitecho.object;

import java.util.List;

public class RepositoriesList {

    @SuppressWarnings("total_count")
    private int totalReposCount;

    @SuppressWarnings("incomplete_results")
    private boolean isCompleteResult;

    @SuppressWarnings("items")
    private List<Repository> repositoryList;

    public RepositoriesList(int totalReposCount,
                            boolean isCompleteResult,
                            List<Repository> repositoryList) {
        this.totalReposCount = totalReposCount;
        this.isCompleteResult = isCompleteResult;
        this.repositoryList = repositoryList;
    }

    public int getTotalReposCount() {
        return totalReposCount;
    }

    public boolean isCompleteResult() {
        return isCompleteResult;
    }

    public List<Repository> getRepositoryList() {
        return repositoryList;
    }
}
