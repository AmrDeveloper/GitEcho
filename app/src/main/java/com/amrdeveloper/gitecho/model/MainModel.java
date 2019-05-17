package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.network.main.RepoViewModel;

public class MainModel implements MainContract.Model {

    @Override
    public void loadingDataFromApi(RepoViewModel viewModel, LifecycleOwner owner, OnLoadListener listener) {
        viewModel.getItemPagedList().observe(owner, listener::onLoadFinish);
    }
}
