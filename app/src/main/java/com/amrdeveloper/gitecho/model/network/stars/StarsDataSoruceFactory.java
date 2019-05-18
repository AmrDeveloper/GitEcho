package com.amrdeveloper.gitecho.model.network.stars;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.amrdeveloper.gitecho.object.Stargazer;

public class StarsDataSoruceFactory extends DataSource.Factory {

    private String username;
    private String repositoryName;
    private MutableLiveData<PageKeyedDataSource<Integer, Stargazer>> starsLiveDataSource = new MutableLiveData<>();

    public StarsDataSoruceFactory(String username,String repositoryName){
        this.username = username;
        this.repositoryName = repositoryName;
    }

    @Override
    public DataSource create() {
        StarsDataSource itemDataSoruce = new StarsDataSource(username,repositoryName);
        starsLiveDataSource.postValue(itemDataSoruce);
        return itemDataSoruce;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Stargazer>> getStarsLiveDataSource() {
        return starsLiveDataSource;
    }
}
