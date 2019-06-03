package com.amrdeveloper.gitecho.model.events;

public class LoginSuccessEvent {

    private String username;

    public LoginSuccessEvent(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
