package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.network.users.UsersViewModel;
import com.amrdeveloper.gitecho.model.contract.UsersContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;

public class UsersModel implements UsersContract.Model {

    @Override
    public void loadingDataFromApi(UsersViewModel viewModel, LifecycleOwner owner, OnLoadListener loadListener) {
        viewModel.getItemPagedList().observe(owner, loadListener::onLoadFinish);
    }
}
