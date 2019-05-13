package com.amrdeveloper.gitecho.model;

import com.amrdeveloper.gitecho.object.Repository;

import java.util.List;

public interface OnLoadListener {
    void onLoadingSuccess(List<Repository> repositoryList);
    void onLoadingFailure();
}
