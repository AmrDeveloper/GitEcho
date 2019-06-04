package com.amrdeveloper.gitecho.model.contract;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.network.issues.IssuesViewModel;
import com.amrdeveloper.gitecho.object.Issue;

public interface IssuesContract {

    public interface Model{
        void loadingDataFromApi(IssuesViewModel viewModel, LifecycleOwner owner);
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
