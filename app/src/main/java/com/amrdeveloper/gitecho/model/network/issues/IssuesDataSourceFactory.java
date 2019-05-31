package com.amrdeveloper.gitecho.model.network.issues;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.amrdeveloper.gitecho.object.Issue;

public class IssuesDataSourceFactory extends DataSource.Factory {

    private String username;
    private String repositoryName;
    private String issueType;

    private MutableLiveData<PageKeyedDataSource<Integer, Issue>> issuesLiveDataSource = new MutableLiveData<>();


    public IssuesDataSourceFactory(String username, String repositoryName, String issueType) {
        this.username = username;
        this.repositoryName = repositoryName;
        this.issueType = issueType;
    }

    @Override
    public DataSource create() {
        IssuesDataSource itemDataSource = new IssuesDataSource(username,repositoryName,issueType);
        issuesLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Issue>> getIssuesLiveDataSource() {
        return issuesLiveDataSource;
    }
}
