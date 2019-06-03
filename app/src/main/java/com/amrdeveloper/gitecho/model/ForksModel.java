package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.ForksContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.forks.ForksViewModel;

import org.greenrobot.eventbus.EventBus;

public class ForksModel implements ForksContract.Model {

    @Override
    public void loadingDataFromApi(ForksViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, repositories -> EventBus.getDefault().post(new LoadFinishEvent<>(repositories)));
    }
}
