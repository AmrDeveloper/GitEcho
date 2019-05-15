package com.amrdeveloper.gitecho.object;

import java.util.List;

public class UserList {

    @SuppressWarnings("total_count")
    private int totalReposCount;

    @SuppressWarnings("incomplete_results")
    private boolean isCompleteResult;

    @SuppressWarnings("items")
    private List<User> userList;

    public UserList(int totalReposCount,
                    boolean isCompleteResult,
                    List<User> userList) {
        this.totalReposCount = totalReposCount;
        this.isCompleteResult = isCompleteResult;
        this.userList = userList;
    }

    public int getTotalReposCount() {
        return totalReposCount;
    }

    public boolean isCompleteResult() {
        return isCompleteResult;
    }

    public List<User> getUserList() {
        return userList;
    }
}
