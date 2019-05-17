package com.amrdeveloper.gitecho.model.network.forks;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.amrdeveloper.gitecho.object.Repository;

public class ForksDataSourceFactory extends DataSource.Factory {

    private String username;
    private String repoName;
    private MutableLiveData<PageKeyedDataSource<Integer, Repository>> repoLiveDataSource = new MutableLiveData<>();

    public ForksDataSourceFactory(String username,String repoName){
        this.username = username;
        this.repoName = repoName;
    }

    @Override
    public DataSource create() {
        ForksDataSource itemDataSource = new ForksDataSource(username,repoName);
        repoLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Repository>> getRepoLiveDataSource() {
        return repoLiveDataSource;
    }
}
