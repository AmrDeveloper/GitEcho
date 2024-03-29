package com.amrdeveloper.gitecho.model.contract;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.network.repos.ReposViewModel;
import com.amrdeveloper.gitecho.object.Repository;

public interface RepositoriesContract {

    public interface Model{
        void loadingDataFromApi(ReposViewModel viewModel, LifecycleOwner owner);
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
