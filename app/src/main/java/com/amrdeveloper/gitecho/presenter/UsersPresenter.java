package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.UsersModel;
import com.amrdeveloper.gitecho.model.network.users.UsersViewModel;
import com.amrdeveloper.gitecho.model.contract.UsersContract;

public class UsersPresenter implements UsersContract.Presenter{

    private UsersContract.View view;
    private UsersContract.Model model;
    private UsersViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public UsersPresenter(UsersContract.View view, UsersViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.view = view;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.model = new UsersModel();
    }

    @Override
    public void startLoadingData(String query) {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner);
    }
}
