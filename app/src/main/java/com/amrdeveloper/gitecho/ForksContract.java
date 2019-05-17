package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.OnLoadListener;
import com.amrdeveloper.gitecho.model.network.RepoViewModel;
import com.amrdeveloper.gitecho.object.Repository;

public interface ForksContract {

    public interface Model{
        void loadingDataFromApi(RepoViewModel viewModel, LifecycleOwner owner, OnLoadListener listener);
    }

    public interface View{
        void onLoadFinish(PagedList<Repository> repositories);

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter{
        void startLoadingData(String username);
    }
}
