package com.amrdeveloper.gitecho.model.network.forks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.object.Repository;

public class ForksViewModel extends ViewModel {

    private static String sUsername;
    private static String sRepoFullName;
    private LiveData<PagedList<Repository>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Repository>> liveDataSource;

    public ForksViewModel(){
        ForksDataSourceFactory itemDataSourceFactory = new ForksDataSourceFactory(sUsername,sRepoFullName);
        liveDataSource = itemDataSourceFactory.getRepoLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ForksDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<Repository>> getItemPagedList() {
        return itemPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Repository>> getLiveDataSource() {
        return liveDataSource;
    }

    public static void setRequestData(String username,String repoName){
        sUsername = username;
        sRepoFullName = repoName;
    }
}
