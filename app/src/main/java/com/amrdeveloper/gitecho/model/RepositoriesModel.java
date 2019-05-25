package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.RepositoriesContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.repos.ReposViewModel;

public class RepositoriesModel implements RepositoriesContract.Model {

    @Override
    public void loadingDataFromApi(ReposViewModel viewModel, LifecycleOwner owner, OnLoadListener loadListener) {
        viewModel.getItemPagedList().observe(owner, loadListener::onLoadFinish);
    }
}
