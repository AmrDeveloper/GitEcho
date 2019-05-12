package com.amrdeveloper.gitecho.model;

public interface OnLoginListener {
    void onLoginSuccess(String username);
    void onLoginFailure();
}
