package com.amrdeveloper.gitecho.model;

import android.content.Context;

import com.amrdeveloper.gitecho.object.Repository;

import java.util.List;

public interface MainContract {

    public interface Model {
        void loadingDataFromApi(Context context, String username, OnLoadListener listener);
    }

    public interface View {
        void onLoadingSuccess(List<Repository> repositoryList);

        void onLoadingFailure();

        void showProgressBar();

        void hideProgressBar();
    }

    public interface Presenter {

        void startLoadingData(String username);

    }
}
