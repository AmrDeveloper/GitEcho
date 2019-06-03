package com.amrdeveloper.gitecho.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.StarsModel;
import com.amrdeveloper.gitecho.model.contract.StarsContract;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.stars.StarsViewModel;
import com.amrdeveloper.gitecho.object.Stargazer;

public class StarsPresenter
        implements StarsContract.Presenter
        , OnLoadListener<PagedList<Stargazer>> {

    private StarsContract.View view;
    private StarsContract.Model model;
    private StarsViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    public StarsPresenter(LifecycleOwner lifecycleOwner, StarsViewModel viewModel, StarsContract.View view){
        this.view = view;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.model = new StarsModel();
    }

    @Override
    public void startLoadingData() {
        view.showProgressBar();
        model.loadingDataFromApi(viewModel,lifecycleOwner,this);
    }

    @Override
    public void onLoadFinish(PagedList<Stargazer> result) {
        view.onLoadFinish(result);
        view.hideProgressBar();
    }
}