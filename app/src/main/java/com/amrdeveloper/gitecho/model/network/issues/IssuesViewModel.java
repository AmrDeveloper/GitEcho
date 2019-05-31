package com.amrdeveloper.gitecho.model.network.issues;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.object.Issue;

public class IssuesViewModel extends ViewModel {

    private static String sUsername;
    private static String sRepoName;
    private static String sIssueType;

    private LiveData<PagedList<Issue>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Issue>> liveDataSource;

    public IssuesViewModel(){
        IssuesDataSourceFactory itemDataSourceFactory = new IssuesDataSourceFactory(sUsername,sRepoName,sIssueType);
        liveDataSource = itemDataSourceFactory.getIssuesLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(IssuesDataSource.PAGE_SIZE)
                        .setInitialLoadSizeHint(IssuesDataSource.INITIAL_LOAD_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<Issue>> getItemPagedList() {
        return itemPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Issue>> getLiveDataSource() {
        return liveDataSource;
    }

    public static void setRequestData(String username,String repoName,String issueType){
        sUsername = username;
        sRepoName = repoName;
        sIssueType = issueType;
    }
}
