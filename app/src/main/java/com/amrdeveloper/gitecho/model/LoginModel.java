package com.amrdeveloper.gitecho.model;

import android.content.Context;

import com.amrdeveloper.gitecho.RetrofitClient;
import com.amrdeveloper.gitecho.object.User;

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
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null){
                            User login = response.body();
                            listener.onLoginSuccess(login.getUsername());
                        }else{
                            listener.onLoginFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        listener.onLoginFailure();
                    }
                });
    }
}
