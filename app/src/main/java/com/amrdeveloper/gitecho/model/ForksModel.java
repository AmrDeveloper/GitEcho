package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.network.forks.ForksViewModel;

public class ForksModel implements ForksContract.Model {

    @Override
    public void loadingDataFromApi(ForksViewModel viewModel, LifecycleOwner owner, OnLoadListener listener) {
        viewModel.getItemPagedList().observe(owner, listener::onLoadFinish);
    }
}
