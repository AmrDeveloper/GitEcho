package com.amrdeveloper.gitecho.model.network.users;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.amrdeveloper.gitecho.object.User;


public class UsersDataSourceFactory extends DataSource.Factory {

    private String query;
    private MutableLiveData<PageKeyedDataSource<Integer, User>> usersLiveDataSource = new MutableLiveData<>();

    public UsersDataSourceFactory(String query){
        this.query = query;
    }

    @Override
    public DataSource create() {
        UsersDataSource usersDataSource = new UsersDataSource(query);
        usersLiveDataSource.postValue(usersDataSource);
        return usersDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, User>> getUsersLiveDataSource() {
        return usersLiveDataSource;
    }
}
