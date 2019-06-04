package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.StarsContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.stars.StarsViewModel;

import org.greenrobot.eventbus.EventBus;

public class StarsModel implements StarsContract.Model {

    @Override
    public void loadingDataFromApi(StarsViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, stargazers -> EventBus.getDefault().post(new LoadFinishEvent<>(stargazers)));
    }

}