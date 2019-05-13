package com.amrdeveloper.gitecho.utils;

public class UrlCreator {

    public static String userUrl(String username){
        return "https://api.github.com/users/".concat(username);
    }

    public static String repositoriesUrl(String username){
        return "https://api.github.com/users/".concat(username).concat("/repos");
    }
}
