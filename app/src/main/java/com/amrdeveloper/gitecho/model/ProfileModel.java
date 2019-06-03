package com.amrdeveloper.gitecho.model;

import com.amrdeveloper.gitecho.model.contract.ProfileContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.Account;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModel implements ProfileContract.Model {

    @Override
    public void getUserInformation(String username) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getOneUser(username)
                .enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if (response.body() != null) {
                            EventBus.getDefault().post(new LoadFinishEvent<Account>(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        //TODO : On Load Failure
                    }
                });
    }
}
