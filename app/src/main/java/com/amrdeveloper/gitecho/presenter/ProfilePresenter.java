package com.amrdeveloper.gitecho.presenter;

import com.amrdeveloper.gitecho.model.contract.ProfileContract;
import com.amrdeveloper.gitecho.model.ProfileModel;

public class ProfilePresenter
        implements ProfileContract.Presenter{

    private ProfileContract.Model model;
    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view){
        this.view = view;
        this.model = new ProfileModel();
    }

    @Override
    public void loadUserInformation(String username) {
        model.getUserInformation(username);
    }
}
