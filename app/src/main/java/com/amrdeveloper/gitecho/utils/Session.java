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

    public boolean isLoged(String username){
        return sessionPreferences.getUsername() != null;
    }

    public boolean logOut(){
        return sessionPreferences.removeUsername();
    }
}
