package com.amrdeveloper.gitecho.model.network.pulls;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.object.PullRequest;

public class PullRequestViewModel extends ViewModel {

    private static String sUsername;
    private static String sRepoName;
    private static String sRequestType;

    private LiveData<PagedList<PullRequest>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, PullRequest>> liveDataSource;

    public PullRequestViewModel(){
        PullRequestDataSourceFactory itemDataSourceFactory = new PullRequestDataSourceFactory(sUsername,sRepoName,sRequestType);
        liveDataSource = itemDataSourceFactory.getRequestsLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PullRequestDataSource.PAGE_SIZE)
                        .setInitialLoadSizeHint(PullRequestDataSource.INITIAL_LOAD_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<PullRequest>> getItemPagedList() {
        return itemPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, PullRequest>> getLiveDataSource() {
        return liveDataSource;
    }

    public static void setRequestData(String username, String repoName, String issueType){
        sUsername = username;
        sRepoName = repoName;
        sRequestType = issueType;
    }
}
