package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.ForksContract;
import com.amrdeveloper.gitecho.model.ForksModel;
import com.amrdeveloper.gitecho.model.network.forks.ForksViewModel;

public class ForksPresenter
        implements ForksContract.Presenter {

    private ForksContract.Model model;
    private ForksContract.View view;
    private ForksViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public ForksPresenter(LifecycleOwner lifecycleOwner, ForksViewModel viewModel, ForksContract.View view){
        this.view = view;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        model = new ForksModel();
    }

    @Override
    public void startLoadingData() {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner);
    }
}
