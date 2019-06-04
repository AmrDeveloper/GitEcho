package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.StarsModel;
import com.amrdeveloper.gitecho.model.contract.StarsContract;
import com.amrdeveloper.gitecho.model.network.stars.StarsViewModel;

public class StarsPresenter
        implements StarsContract.Presenter {

    private StarsContract.View view;
    private StarsContract.Model model;
    private StarsViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public StarsPresenter(LifecycleOwner lifecycleOwner, StarsViewModel viewModel, StarsContract.View view){
        this.view = view;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.model = new StarsModel();
    }

    @Override
    public void startLoadingData() {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner);
    }
}