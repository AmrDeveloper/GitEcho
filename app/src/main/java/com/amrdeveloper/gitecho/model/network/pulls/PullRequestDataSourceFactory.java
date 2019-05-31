package com.amrdeveloper.gitecho.model.network.pulls;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.amrdeveloper.gitecho.object.PullRequest;

public class PullRequestDataSourceFactory extends DataSource.Factory {

    private String username;
    private String repositoryName;
    private String requestType;

    private MutableLiveData<PageKeyedDataSource<Integer, PullRequest>> requestLiveDataSource = new MutableLiveData<>();

    public PullRequestDataSourceFactory(String username, String repositoryName, String requestType) {
        this.username = username;
        this.repositoryName = repositoryName;
        this.requestType = requestType;
    }

    @Override
    public DataSource create() {
        PullRequestDataSource itemDataSource = new PullRequestDataSource(username,repositoryName,requestType);
        requestLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, PullRequest>> getRequestsLiveDataSource() {
        return requestLiveDataSource;
    }
}
