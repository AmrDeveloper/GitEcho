package com.amrdeveloper.gitecho.model;

import com.amrdeveloper.gitecho.model.contract.RepositoryContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.Repository;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryModel implements RepositoryContract.Model {

    @Override
    public void getRepositoryInformation(String username, String repoName) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepository(username,repoName)
                .enqueue(new Callback<Repository>() {
                    @Override
                    public void onResponse(Call<Repository> call, Response<Repository> response) {
                        if(response.body() != null){
                            EventBus.getDefault().post(new LoadFinishEvent<>(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Repository> call, Throwable t) {

                    }
                });
    }
}
