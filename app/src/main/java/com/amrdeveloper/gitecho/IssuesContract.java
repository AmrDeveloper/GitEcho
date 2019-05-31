package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.Issue;

public interface IssuesContract {

    public interface Model{
        void loadingDataFromApi(IssuesViewModel viewModel, LifecycleOwner owner, OnLoadListener listener);
    }

    public interface View{
        void onLoadFinish(PagedList<Issue> issues);

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter{
        void startLoadingData();
    }
}
