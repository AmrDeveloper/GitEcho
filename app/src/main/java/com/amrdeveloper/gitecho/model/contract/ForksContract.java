package com.amrdeveloper.gitecho.model.contract;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.network.forks.ForksViewModel;
import com.amrdeveloper.gitecho.object.Repository;

public interface ForksContract {

    public interface Model{
        void loadingDataFromApi(ForksViewModel viewModel, LifecycleOwner owner);
    }

    public interface View{
        void onLoadFinish(PagedList<Repository> repositories);

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter{
        void startLoadingData();
    }
}
