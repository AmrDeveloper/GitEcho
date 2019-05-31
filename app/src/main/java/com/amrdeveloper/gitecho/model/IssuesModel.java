package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.IssuesContract;
import com.amrdeveloper.gitecho.model.network.issues.IssuesViewModel;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;

public class IssuesModel implements IssuesContract.Model {
    @Override
    public void loadingDataFromApi(IssuesViewModel viewModel, LifecycleOwner owner, OnLoadListener listener) {
        viewModel.getItemPagedList().observe(owner, listener::onLoadFinish);
    }
}
