package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.RepositoriesModel;
import com.amrdeveloper.gitecho.model.contract.RepositoriesContract;
import com.amrdeveloper.gitecho.model.network.repos.ReposViewModel;

public class RepositoriesPresenter
        implements RepositoriesContract.Presenter{


    private RepositoriesContract.View view;
    private RepositoriesContract.Model model;
    private ReposViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public RepositoriesPresenter(RepositoriesContract.View view, ReposViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.view = view;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.model = new RepositoriesModel();
    }

    @Override
    public void startLoadingData(String query) {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner);
    }
}
