package com.amrdeveloper.gitecho.model.contract;

import com.amrdeveloper.gitecho.model.listener.OnLoadListener;
import com.amrdeveloper.gitecho.object.Account;

public interface ProfileContract {

    public interface Model{
        void getUserInformation(String username, OnLoadListener<Account> listener);
    }

    public interface View{
        void onLoadFinish(Account user);
    }

    public interface Presenter{
        void loadUserInformation(String username);
    }
}
