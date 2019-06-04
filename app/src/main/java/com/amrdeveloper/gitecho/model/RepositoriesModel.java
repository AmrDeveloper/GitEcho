package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.RepositoriesContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.repos.ReposViewModel;

import org.greenrobot.eventbus.EventBus;

public class RepositoriesModel implements RepositoriesContract.Model {

    @Override
    public void loadingDataFromApi(ReposViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, repositories -> EventBus.getDefault().post(new LoadFinishEvent<>(repositories)));
    }
}
