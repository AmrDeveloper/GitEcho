package com.amrdeveloper.gitecho.presenter;

import android.content.Context;

import com.amrdeveloper.gitecho.model.contract.LoginContract;
import com.amrdeveloper.gitecho.model.LoginModel;

public class LoginPresenter
        implements LoginContract.Presenter {

    private Context context;
    private LoginContract.Model model;
    private LoginContract.View view;

    public LoginPresenter(Context context,LoginContract.View view){
        this.context = context;
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void onStartLogin(String username) {
        boolean isValidUsername = model.isUsernameValid(username);
        if (isValidUsername) {
            view.showProgressBar();
            model.makeLoginRequest(context, username);
        } else {
            view.hideProgressBar();
            view.onLoginFailure();
        }
    }
}
