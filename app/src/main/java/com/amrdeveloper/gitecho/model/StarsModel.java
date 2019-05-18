package com.amrdeveloper.gitecho.model;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.StarsContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.stars.StarsViewModel;

public class StarsModel implements StarsContract.Model {

    @Override
    public void loadingDataFromApi(StarsViewModel viewModel, LifecycleOwner owner, OnLoadListener listener) {
        //viewModel.getItemPagedList().observe(owner, listener::onLoadFinish);
    }

}
