package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.PullRequestModel;
import com.amrdeveloper.gitecho.model.contract.PullRequestContract;
import com.amrdeveloper.gitecho.model.network.pulls.PullRequestViewModel;

public class PullRequestPresenter
        implements PullRequestContract.Presenter{

    private PullRequestContract.Model model;
    private PullRequestContract.View view;
    private PullRequestViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public PullRequestPresenter(LifecycleOwner lifecycleOwner, PullRequestViewModel viewModel, PullRequestContract.View view){
        this.view = view;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        model = new PullRequestModel();
    }

    @Override
    public void startLoadingData() {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner);
    }
}
