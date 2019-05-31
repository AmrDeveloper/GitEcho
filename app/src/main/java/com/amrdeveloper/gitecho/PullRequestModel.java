package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;


public class PullRequestModel implements PullRequestContract.Model {
    @Override
    public void loadingDataFromApi(PullRequestViewModel viewModel, LifecycleOwner owner, OnLoadListener listener) {
        viewModel.getItemPagedList().observe(owner, listener::onLoadFinish);
    }
}
