package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.MainContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.repo.RepoViewModel;

import org.greenrobot.eventbus.EventBus;

public class MainModel implements MainContract.Model {

    @Override
    public void loadingDataFromApi(RepoViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, repositories -> EventBus.getDefault().post(new LoadFinishEvent<>(repositories)));
    }
}
