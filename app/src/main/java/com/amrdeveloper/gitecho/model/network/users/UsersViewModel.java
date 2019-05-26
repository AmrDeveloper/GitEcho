package com.amrdeveloper.gitecho.model.network.users;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.object.Account;
import com.amrdeveloper.gitecho.object.User;

public class UsersViewModel extends ViewModel {

    public static String sQuery;
    private LiveData<PagedList<Account>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, User>> liveDataSource;

    public UsersViewModel() {
        UsersDataSourceFactory itemDataSourceFactory = new UsersDataSourceFactory(sQuery);
        liveDataSource = itemDataSourceFactory.getUsersLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(UsersDataSource.PAGE_SIZE)
                        .setInitialLoadSizeHint(UsersDataSource.INITIAL_LOAD_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<Account>> getItemPagedList() {
        return itemPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, User>> getLiveDataSource() {
        return liveDataSource;
    }

    public static void setSearchQuery(String query) {
        sQuery = query;
    }
}
