package com.amrdeveloper.gitecho.presenter;

import android.content.Context;

import com.amrdeveloper.gitecho.model.LoginContract;
import com.amrdeveloper.gitecho.model.LoginModel;
import com.amrdeveloper.gitecho.model.OnLoginListener;

public class LoginPresenter
        implements LoginContract.Presenter
        , OnLoginListener {

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
        if(isValidUsername){
            view.showProgressBar();
            model.makeLoginRequest(context,username,this);
        }else{
            view.hideProgressBar();
        }
    }

    @Override
    public void onLoginSuccess(String username) {
        view.hideProgressBar();
        view.onLoginSuccess(username);
    }

    @Override
    public void onLoginFailure() {
        view.hideProgressBar();
        view.onLoginFailure();
    }
}
