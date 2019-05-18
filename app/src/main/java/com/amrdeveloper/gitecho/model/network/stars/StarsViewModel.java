package com.amrdeveloper.gitecho.model.network.stars;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.object.Stargazer;

public class StarsViewModel extends ViewModel {

    private static String sUsername;
    private static String sRepoFullName;
    private LiveData<PagedList<Stargazer>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Stargazer>> liveDataSource;

    public StarsViewModel(){
        StarsDataSoruceFactory itemDataSourceFactory = new StarsDataSoruceFactory(sUsername,sRepoFullName);
        liveDataSource = itemDataSourceFactory.getStarsLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(StarsDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<Stargazer>> getItemPagedList() {
        return itemPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Stargazer>> getLiveDataSource() {
        return liveDataSource;
    }

    public static void setRequestData(String username,String repoName){
        sUsername = username;
        sRepoFullName = repoName;
    }
}
