package com.amrdeveloper.gitecho;

import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.databinding.ActivityUsersBinding;
import com.amrdeveloper.gitecho.object.User;
import com.amrdeveloper.gitecho.utils.Consts;

public class UsersActivity extends AppCompatActivity implements UsersContract.View{

    private ActivityUsersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_users);

        Intent intent = getIntent();
        String query = intent.getStringExtra(Consts.QUERY);
    }

    @Override
    public void onLoadFinish(PagedList<User> organizations) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
