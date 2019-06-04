package com.amrdeveloper.gitecho.presenter;

import com.amrdeveloper.gitecho.model.RepositoryModel;
import com.amrdeveloper.gitecho.model.contract.RepositoryContract;

public class RepositoryPresenter implements RepositoryContract.Presenter {

    private RepositoryContract.Model model;
    private RepositoryContract.View view;

    public RepositoryPresenter(RepositoryContract.View view) {
        this.view = view;
        this.model = new RepositoryModel();
    }

    @Override
    public void loadRepositoryInformation(String username, String repoName) {
        model.getRepositoryInformation(username, repoName);
    }
}
