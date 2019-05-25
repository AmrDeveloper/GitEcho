package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.RepositoriesContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;

public class RepositoriesModel implements RepositoriesContract.Model {

    @Override
    public void loadingDataFromApi(ReposViewModel viewModel, LifecycleOwner owner, OnLoadListener loadListener) {
        viewModel.getItemPagedList().observe(owner, loadListener::onLoadFinish);
    }
}
