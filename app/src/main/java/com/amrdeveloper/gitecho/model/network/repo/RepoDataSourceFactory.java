package com.amrdeveloper.gitecho.model.network.repo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.amrdeveloper.gitecho.object.Repository;

public class RepoDataSourceFactory extends DataSource.Factory {

    private String username;
    private MutableLiveData<PageKeyedDataSource<Integer, Repository>> repoLiveDataSource = new MutableLiveData<>();

    public RepoDataSourceFactory(String username){
        this.username = username;
    }

    @Override
    public DataSource create() {
        RepoDataSource itemDataSource = new RepoDataSource(username);
        repoLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Repository>> getRepoLiveDataSource() {
        return repoLiveDataSource;
    }
}
