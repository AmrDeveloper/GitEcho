package com.amrdeveloper.gitecho.utils;

import android.content.Context;

public class Session {

    private SessionPreferences sessionPreferences;

    public Session(Context context){
        this.sessionPreferences = new SessionPreferences(context);
    }

    public boolean login(String username){
        return sessionPreferences.setUsername(username);
    }

    public boolean isLogged(){
        return !sessionPreferences.getUsername().isEmpty();
    }

    public boolean logOut(){
        return sessionPreferences.removeUsername();
    }

    public String getUsername(){
        return sessionPreferences.getUsername();
    }
}
