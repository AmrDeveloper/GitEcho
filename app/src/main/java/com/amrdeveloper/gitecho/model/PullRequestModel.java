package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.PullRequestContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.pulls.PullRequestViewModel;


public class PullRequestModel implements PullRequestContract.Model {
    @Override
    public void loadingDataFromApi(PullRequestViewModel viewModel, LifecycleOwner owner, OnLoadListener listener) {
        viewModel.getItemPagedList().observe(owner, listener::onLoadFinish);
    }
}
