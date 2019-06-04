package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.amrdeveloper.gitecho.model.contract.IssuesContract;
import com.amrdeveloper.gitecho.model.IssuesModel;
import com.amrdeveloper.gitecho.model.network.issues.IssuesViewModel;

public class IssuesPresenter implements IssuesContract.Presenter{

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
        model.loadingDataFromApi(viewModel,lifecycleOwner);
    }
}
