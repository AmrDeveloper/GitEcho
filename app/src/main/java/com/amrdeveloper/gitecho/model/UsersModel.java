package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.users.UsersViewModel;
import com.amrdeveloper.gitecho.model.contract.UsersContract;

import org.greenrobot.eventbus.EventBus;

public class UsersModel implements UsersContract.Model {

    @Override
    public void loadingDataFromApi(UsersViewModel viewModel, LifecycleOwner owner) {
        viewModel.getItemPagedList().observe(owner, accounts -> EventBus.getDefault().post(new LoadFinishEvent<>(accounts)));
    }
}
