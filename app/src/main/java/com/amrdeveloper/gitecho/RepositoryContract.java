package com.amrdeveloper.gitecho;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.Repository;

public interface RepositoryContract {

    public interface Model{
        void getRepositoryInformation(String username, String repoName, OnLoadListener<Repository> listener);
    }

    public interface View{
        void onLoadFinish(Repository repository);
    }

    public interface Presenter{
        void loadRepositoryInformation(String username, String repoName);
    }
}
