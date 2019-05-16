package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.RepoViewModel;
import com.amrdeveloper.gitecho.model.MainContract;
import com.amrdeveloper.gitecho.model.MainModel;
import com.amrdeveloper.gitecho.model.OnLoadListener;
import com.amrdeveloper.gitecho.object.Repository;

public class MainPresenter
        implements MainContract.Presenter,
        OnLoadListener<PagedList<Repository>> {

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
        model.loadingDataFromApi(viewModel,lifecycleOwner,this);
    }

    @Override
    public void onLoadFinish(PagedList<Repository> repositories) {
        view.hideProgressBar();
        view.onLoadFinish(repositories);
    }
}
