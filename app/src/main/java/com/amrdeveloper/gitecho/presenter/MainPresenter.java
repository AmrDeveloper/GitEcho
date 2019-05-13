package com.amrdeveloper.gitecho.presenter;

import android.content.Context;

import com.amrdeveloper.gitecho.model.MainContract;
import com.amrdeveloper.gitecho.model.MainModel;
import com.amrdeveloper.gitecho.model.OnLoadListener;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.utils.UrlCreator;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, OnLoadListener {

    private Context context;
    private MainContract.Model model;
    private MainContract.View view;

    public MainPresenter(Context context,MainContract.View view){
        this.context = context;
        this.view = view;
        this.model = new MainModel();
    }

    @Override
    public void startLoadingData(String username) {
        view.showProgressBar();
        String url = UrlCreator.repositoriesUrl(username);
        model.loadingDataFromApi(context,url,this);
    }

    @Override
    public void onLoadingSuccess(List<Repository> repositoryList) {
        view.hideProgressBar();
        view.onLoadingSuccess(repositoryList);
    }

    @Override
    public void onLoadingFailure() {
        view.hideProgressBar();
        view.onLoadingFailure();
    }
}
