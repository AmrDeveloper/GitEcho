package com.amrdeveloper.gitecho.presenter;

import com.amrdeveloper.gitecho.model.OnLoadListener;
import com.amrdeveloper.gitecho.model.ProfileContract;
import com.amrdeveloper.gitecho.model.ProfileModel;
import com.amrdeveloper.gitecho.object.User;

public class ProfilePresenter
        implements ProfileContract.Presenter,
        OnLoadListener<User>{

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
    public void onLoadFinish(User user) {
        view.onLoadFinish(user);
    }
}
