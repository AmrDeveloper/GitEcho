package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.PullRequestModel;
import com.amrdeveloper.gitecho.model.contract.PullRequestContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.pulls.PullRequestViewModel;
import com.amrdeveloper.gitecho.object.PullRequest;

public class PullRequestPresenter
        implements PullRequestContract.Presenter
        , OnLoadListener<PagedList<PullRequest>> {

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
        model.loadingDataFromApi(viewModel,lifecycleOwner,this);
    }

    @Override
    public void onLoadFinish(PagedList<PullRequest> data) {
        view.onLoadFinish(data);
        view.hideProgressBar();
    }
}
