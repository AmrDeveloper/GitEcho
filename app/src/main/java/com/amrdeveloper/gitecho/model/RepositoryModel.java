package com.amrdeveloper.gitecho.model;

import com.amrdeveloper.gitecho.model.contract.RepositoryContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryModel implements RepositoryContract.Model {

    @Override
    public void getRepositoryInformation(String username, String repoName, OnLoadListener<Repository> listener) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepository(username,repoName)
                .enqueue(new Callback<Repository>() {
                    @Override
                    public void onResponse(Call<Repository> call, Response<Repository> response) {
                        if(response.body() != null){
                            listener.onLoadFinish(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Repository> call, Throwable t) {

                    }
                });
    }
}
