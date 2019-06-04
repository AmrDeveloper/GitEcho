package com.amrdeveloper.gitecho.model.contract;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.network.stars.StarsViewModel;
import com.amrdeveloper.gitecho.object.Stargazer;

public interface StarsContract {

    public interface Model{
        void loadingDataFromApi(StarsViewModel viewModel, LifecycleOwner owner);
    }

    public interface View{
        void onLoadFinish(PagedList<Stargazer> stargazers);

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter{
        void startLoadingData();
    }
}
