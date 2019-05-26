package com.amrdeveloper.gitecho.model;

import android.content.Context;

import com.amrdeveloper.gitecho.model.contract.LoginContract;
import com.amrdeveloper.gitecho.model.listener.OnLoginListener;
import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    @Override
    public boolean isUsernameValid(String username) {
        return !username.isEmpty();
    }

    @Override
    public void makeLoginRequest(Context context, String username, OnLoginListener listener) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getOneUser(username)
                .enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if(response.body() != null){
                            Account login = response.body();
                            listener.onLoginSuccess(login.getUsername());
                        }else{
                            listener.onLoginFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        listener.onLoginFailure();
                    }
                });
    }
}
