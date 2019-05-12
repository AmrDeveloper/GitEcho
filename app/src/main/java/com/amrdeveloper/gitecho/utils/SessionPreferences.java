package com.amrdeveloper.gitecho.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionPreferences {

    private Context context;
    private static final String USERNAME_KEY = "username";
    private static final String PREFERENCES_NAME = "session";

    public SessionPreferences(Context context) {
        this.context = context;
    }

    /**
     * @param username : current username
     * @return : true if username is inserted on username key
     */
    public boolean setUsername(String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        return editor.commit();
    }

    /**
     * @return : value of username key
     */
    public String getUsername(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME_KEY,"");
    }

    /**
     * Delete all the shared preferences:
     * @return : true if all is deleted
     */
    public boolean removeUsername(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().clear().commit();
    }
}
