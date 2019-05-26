package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.contract.UsersContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.User;

public class UsersPresenter
        implements UsersContract.Presenter,
        OnLoadListener<PagedList<User>> {

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
        model.loadingDataFromApi(viewModel,lifecycleOwner,this);
    }

    @Override
    public void onLoadFinish(PagedList<User> data) {
        view.onLoadFinish(data);
        view.hideProgressBar();
    }
}
