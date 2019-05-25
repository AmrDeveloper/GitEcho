package com.amrdeveloper.gitecho.model.network.repos;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.amrdeveloper.gitecho.object.Repository;

public class ReposDataSourceFactory extends DataSource.Factory {

    private String query;
    private MutableLiveData<PageKeyedDataSource<Integer, Repository>> repoLiveDataSource = new MutableLiveData<>();

    public ReposDataSourceFactory(String query){
        this.query = query;
    }

    @Override
    public DataSource create() {
        ReposDataSource reposDataSource = new ReposDataSource(query);
        repoLiveDataSource.postValue(reposDataSource);
        return reposDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Repository>> getRepoLiveDataSource() {
        return repoLiveDataSource;
    }
}
