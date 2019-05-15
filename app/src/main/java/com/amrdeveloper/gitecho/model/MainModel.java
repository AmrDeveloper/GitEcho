package com.amrdeveloper.gitecho.model;

import android.content.Context;

import com.amrdeveloper.gitecho.RetrofitClient;
import com.amrdeveloper.gitecho.object.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements MainContract.Model {

    @Override
    public void loadingDataFromApi(Context context, String username, OnLoadListener listener) {
        RetrofitClient.getInstance()
                .getGithubService()
                .userListRepos(username)
                .enqueue(new Callback<List<Repository>>() {
                    @Override
                    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                        if (response.body() != null) {
                            listener.onLoadingSuccess(response.body());
                        }else{
                            listener.onLoadingFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repository>> call, Throwable t) {
                        listener.onLoadingFailure();
                    }
                });
    }
}
