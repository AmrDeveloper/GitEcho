package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.network.repo.RepoViewModel;
import com.amrdeveloper.gitecho.model.contract.MainContract;
import com.amrdeveloper.gitecho.model.MainModel;

public class MainPresenter
        implements MainContract.Presenter{

    private MainContract.Model model;
    private MainContract.View view;
    private RepoViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public MainPresenter(LifecycleOwner lifecycleOwner, RepoViewModel viewModel, MainContract.View view){
        this.lifecycleOwner = lifecycleOwner;
        this.view = view;
        this.viewModel = viewModel;
        this.model = new MainModel();
    }

    @Override
    public void startLoadingData(String username) {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner);
    }
}
