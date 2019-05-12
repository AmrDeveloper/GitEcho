package com.amrdeveloper.gitecho.model;

import android.content.Context;

public class LoginContract {
    
    public interface Model {
        boolean isUsernameValid(String username);

        void makeLoginRequest(Context context, String url, OnLoginListener listener);
    }

    public interface View {
        void showErrorMessage(String message);

        void onLoginSuccess();

        void onLoginFailure();

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter {
        void onStartLogin(String username);
    }
}
