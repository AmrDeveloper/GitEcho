package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.User;

public interface UsersContract {

    public interface Model{
        void loadingDataFromApi(UsersViewModel viewModel, LifecycleOwner owner, OnLoadListener loadListener);
    }

    public interface View{

        void onLoadFinish(PagedList<User> organizations);

        void showProgressBar();

        void hideProgressBar();

    }

    public interface Presenter{

        void startLoadingData(String query);
    }
}
