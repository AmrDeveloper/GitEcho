package com.amrdeveloper.gitecho.model;

import android.arch.paging.PagedList;

import com.amrdeveloper.gitecho.object.Repository;

public interface OnLoadListener {
    void onLoadFinish(PagedList<Repository> repositories);
}
