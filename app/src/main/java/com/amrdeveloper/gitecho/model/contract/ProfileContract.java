package com.amrdeveloper.gitecho.model.contract;

import com.amrdeveloper.gitecho.object.Account;

public interface ProfileContract {

    public interface Model{
        void getUserInformation(String username);
    }

    public interface View{
        void onLoadFinish(Account user);
    }

    public interface Presenter{
        void loadUserInformation(String username);
    }
}
