package com.amrdeveloper.gitecho;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.model.network.main.RepoDataSource;
import com.amrdeveloper.gitecho.object.Repository;

public class ReposViewModel extends ViewModel {

    public static String sQuery;
    private LiveData<PagedList<Repository>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Repository>> liveDataSource;

    public ReposViewModel(){
        ReposDataSourceFactory itemDataSourceFactory = new ReposDataSourceFactory(sQuery);
        liveDataSource = itemDataSourceFactory.getRepoLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ReposDataSource.PAGE_SIZE)
                        .setInitialLoadSizeHint(ReposDataSource.INITIAL_LOAD_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<Repository>> getItemPagedList() {
        return itemPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Repository>> getLiveDataSource() {
        return liveDataSource;
    }

    public static void setSearchQuery(String query){
        sQuery = query;
    }
}
