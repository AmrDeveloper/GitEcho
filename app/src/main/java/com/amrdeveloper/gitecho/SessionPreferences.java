package com.amrdeveloper.gitecho;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionPreferences {

    private Context context;
    private static final String USERNAME_KEY = "username";
    private static final String PREFERENCES_NAME = "session";

    public SessionPreferences(Context context) {
        this.context = context;
    }

    public boolean setUsername(String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        return editor.commit();
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME_KEY,"");
    }

    public boolean removeUsername(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().clear().commit();
    }
}
