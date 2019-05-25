package com.amrdeveloper.gitecho;

import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.databinding.ActivityRepositoriesBinding;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.utils.Consts;

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View{

    private ActivityRepositoriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_repositories);

        Intent intent = getIntent();
        String query = intent.getStringExtra(Consts.QUERY);
    }

    @Override
    public void onLoadFinish(PagedList<Repository> organizations) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
