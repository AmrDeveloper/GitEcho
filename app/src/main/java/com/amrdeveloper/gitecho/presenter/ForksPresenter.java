package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.ForksContract;
import com.amrdeveloper.gitecho.model.ForksModel;
import com.amrdeveloper.gitecho.model.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.forks.ForksViewModel;
import com.amrdeveloper.gitecho.object.Repository;

public class ForksPresenter
        implements ForksContract.Presenter,
        OnLoadListener<PagedList<Repository>> {

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
    public void startLoadingData(String username) {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner,this);
    }

    @Override
    public void onLoadFinish(PagedList<Repository> repositories) {
        view.onLoadFinish(repositories);
        view.hideProgressBar();
    }
}
