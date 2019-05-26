package com.amrdeveloper.gitecho.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersList {

    @SerializedName("items")
    private List<User> usersList;

    public UsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<User> getUsersList() {
        return usersList;
    }
}
