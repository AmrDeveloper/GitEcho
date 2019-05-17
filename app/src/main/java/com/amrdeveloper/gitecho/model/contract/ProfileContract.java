package com.amrdeveloper.gitecho.model.contract;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
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
