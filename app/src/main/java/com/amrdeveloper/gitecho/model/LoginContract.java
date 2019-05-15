package com.amrdeveloper.gitecho.model;

import android.content.Context;

public interface LoginContract {

    public interface Model {
        boolean isUsernameValid(String username);

        void makeLoginRequest(Context context, String username, OnLoginListener listener);
    }

    public interface View {
        void onLoginSuccess(String username);

        void onLoginFailure();

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter {
        void onStartLogin(String username);
    }
}
