package com.amrdeveloper.gitecho.model;

import com.amrdeveloper.gitecho.object.User;

public interface ProfileContract {

    public interface Model{
        void getUserInformation(String username, OnLoadListener<User> listener);
    }

    public interface View{
        void onLoadFinish(User user);
    }

    public interface Presenter{
        void loadUserInformation(String username);
    }
}
