package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.RepoViewModel;

public class ForksModel implements ForksContract.Model {

    @Override
    public void loadingDataFromApi(RepoViewModel viewModel, LifecycleOwner owner, OnLoadListener listener) {
        viewModel.getItemPagedList().observe(owner, listener::onLoadFinish);
    }
}
