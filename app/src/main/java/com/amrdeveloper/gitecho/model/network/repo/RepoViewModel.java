package com.amrdeveloper.gitecho.model.network.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.object.Repository;

public class RepoViewModel extends ViewModel {

    public static String sUsername;
    private LiveData<PagedList<Repository>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Repository>> liveDataSource;

    public RepoViewModel(){
        RepoDataSourceFactory itemDataSourceFactory = new RepoDataSourceFactory(sUsername);
        liveDataSource = itemDataSourceFactory.getRepoLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(RepoDataSource.PAGE_SIZE)
                        .setInitialLoadSizeHint(RepoDataSource.INITIAL_LOAD_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<Repository>> getItemPagedList() {
        return itemPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Repository>> getLiveDataSource() {
        return liveDataSource;
    }

    public static void setUsername(String username){
        sUsername = username;
    }
}
