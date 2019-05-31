package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.Issue;

public class IssuesPresenter
        implements IssuesContract.Presenter,
        OnLoadListener<PagedList<Issue>> {

    private IssuesContract.Model model;
    private IssuesContract.View view;
    private IssuesViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public IssuesPresenter(LifecycleOwner lifecycleOwner, IssuesViewModel viewModel, IssuesContract.View view){
        this.view = view;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        model = new IssuesModel();
    }

    @Override
    public void startLoadingData() {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner,this);
    }

    @Override
    public void onLoadFinish(PagedList<Issue> data) {
        view.onLoadFinish(data);
        view.hideProgressBar();
    }
}
