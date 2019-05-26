package com.amrdeveloper.gitecho.presenter;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.model.contract.ProfileContract;
import com.amrdeveloper.gitecho.model.ProfileModel;
import com.amrdeveloper.gitecho.object.Account;

public class ProfilePresenter
        implements ProfileContract.Presenter,
        OnLoadListener<Account>{

    private ProfileContract.Model model;
    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view){
        this.view = view;
        this.model = new ProfileModel();
    }

    @Override
    public void loadUserInformation(String username) {
        model.getUserInformation(username,this);
    }

    @Override
    public void onLoadFinish(Account user) {
        view.onLoadFinish(user);
    }
}
