package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.PullRequest;

public interface PullRequestContract {

    public interface Model{
        void loadingDataFromApi(PullRequestViewModel viewModel, LifecycleOwner owner, OnLoadListener listener);
    }

    public interface View{
        void onLoadFinish(PagedList<PullRequest> issues);

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter{
        void startLoadingData();
    }
}
