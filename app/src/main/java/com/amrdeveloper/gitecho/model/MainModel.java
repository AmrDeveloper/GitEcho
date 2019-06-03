package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;

import com.amrdeveloper.gitecho.model.contract.MainContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.repo.RepoViewModel;
import com.amrdeveloper.gitecho.object.Repository;

import org.greenrobot.eventbus.EventBus;

public class MainModel implements MainContract.Model {

    @Override
    public void loadingDataFromApi(RepoViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, repositories -> EventBus.getDefault().post(new LoadFinishEvent<>(repositories)));
    }
}
