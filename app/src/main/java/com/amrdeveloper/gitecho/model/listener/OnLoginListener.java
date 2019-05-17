package com.amrdeveloper.gitecho.model.listener;

public interface OnLoginListener {
    void onLoginSuccess(String username);
    void onLoginFailure();
}
