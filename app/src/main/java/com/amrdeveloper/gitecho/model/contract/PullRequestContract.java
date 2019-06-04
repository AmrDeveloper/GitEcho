package com.amrdeveloper.gitecho.model.contract;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.network.pulls.PullRequestViewModel;
import com.amrdeveloper.gitecho.object.PullRequest;

public interface PullRequestContract {

    public interface Model{
        void loadingDataFromApi(PullRequestViewModel viewModel, LifecycleOwner owner);
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
