package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.RepositoryContract;
import com.amrdeveloper.gitecho.RepositoryPresenter;
import com.amrdeveloper.gitecho.databinding.ActivityRepositoryBinding;
import com.amrdeveloper.gitecho.object.Repository;

public class RepositoryActivity extends AppCompatActivity implements RepositoryContract.View {

    private ActivityRepositoryBinding binding;
    private RepositoryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_repository);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String repositoryName = intent.getStringExtra("repositoryName");

        presenter = new RepositoryPresenter(this);
        presenter.loadRepositoryInformation(username,repositoryName);
    }

    @Override
    public void onLoadFinish(Repository repository) {

    }
}
