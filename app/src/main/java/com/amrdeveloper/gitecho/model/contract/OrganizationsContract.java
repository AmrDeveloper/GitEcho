package com.amrdeveloper.gitecho.model.contract;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.OrganizationsViewModel;
import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.Organiztion;

public interface OrganizationsContract {

    public interface Model{
        void loadingDataFromApi(OrganizationsViewModel viewModel, LifecycleOwner owner, OnLoadListener loadListener);
    }

    public interface View{

        void onLoadFinish(PagedList<Organiztion> organizations);

        void showProgressBar();

        void hideProgressBar();

    }

    public interface Presenter{

        void startLoadingData(String query);
    }
}
