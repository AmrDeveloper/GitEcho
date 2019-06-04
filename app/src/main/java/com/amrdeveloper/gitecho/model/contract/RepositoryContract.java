package com.amrdeveloper.gitecho.model.contract;

import com.amrdeveloper.gitecho.object.Repository;

public interface RepositoryContract {

    public interface Model{
        void getRepositoryInformation(String username, String repoName);
    }

    public interface View{
        void onLoadFinish(Repository repository);
    }

    public interface Presenter{
        void loadRepositoryInformation(String username, String repoName);
    }
}
