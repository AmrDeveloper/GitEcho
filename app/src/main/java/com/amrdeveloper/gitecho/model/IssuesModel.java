package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.IssuesContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.issues.IssuesViewModel;

import org.greenrobot.eventbus.EventBus;

public class IssuesModel implements IssuesContract.Model {
    @Override
    public void loadingDataFromApi(IssuesViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, issues -> EventBus.getDefault().post(new LoadFinishEvent<>(issues)));
    }
}
