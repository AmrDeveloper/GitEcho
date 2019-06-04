package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.PullRequestContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.pulls.PullRequestViewModel;

import org.greenrobot.eventbus.EventBus;

public class PullRequestModel implements PullRequestContract.Model {
    @Override
    public void loadingDataFromApi(PullRequestViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, pullRequests -> EventBus.getDefault().post(new LoadFinishEvent<>(pullRequests)));
    }
}
