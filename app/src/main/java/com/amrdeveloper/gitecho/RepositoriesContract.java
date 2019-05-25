package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.Repository;

public interface RepositoriesContract {

    public interface Model{
        void loadingDataFromApi(ReposViewModel viewModel, LifecycleOwner owner, OnLoadListener loadListener);
    }

    public interface View{

        void onLoadFinish(PagedList<Repository> organizations);

        void showProgressBar();

        void hideProgressBar();

    }

    public interface Presenter{

        void startLoadingData(String query);
    }
}
