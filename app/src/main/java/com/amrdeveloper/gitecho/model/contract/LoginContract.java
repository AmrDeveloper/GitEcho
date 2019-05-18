package com.amrdeveloper.gitecho.model.contract;

import android.content.Context;

import com.amrdeveloper.gitecho.model.listener.OnLoginListener;

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