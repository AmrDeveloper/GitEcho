package com.amrdeveloper.gitecho.model;

import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModel implements ProfileContract.Model {

    @Override
    public void getUserInformation(String username, OnLoadListener<User> listener) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getOneUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            listener.onLoadFinish(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        //TODO : On Load Failure
                    }
                });
    }
}
