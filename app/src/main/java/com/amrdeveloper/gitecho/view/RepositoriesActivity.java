package com.amrdeveloper.gitecho.view;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.model.network.repos.ReposViewModel;
import com.amrdeveloper.gitecho.presenter.RepositoriesPresenter;
import com.amrdeveloper.gitecho.model.contract.RepositoriesContract;
import com.amrdeveloper.gitecho.adapter.RepoPagedListAdapter;
import com.amrdeveloper.gitecho.databinding.ActivityRepositoriesBinding;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.utils.Consts;

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {

    private ActivityRepositoriesBinding binding;
    private RepoPagedListAdapter repoRecyclerAdapter;
    private RepositoriesContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repositories);

        Intent intent = getIntent();
        String query = intent.getStringExtra(Consts.QUERY);
        setRecyclerViewSettings();

        ReposViewModel.setSearchQuery(query);
        ReposViewModel itemViewModel = ViewModelProviders.of(this).get(ReposViewModel.class);

        presenter = new RepositoriesPresenter(this,itemViewModel,this);
        presenter.startLoadingData(query);
    }

    private void setRecyclerViewSettings() {
        repoRecyclerAdapter = new RepoPagedListAdapter(this);
        binding.reposRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.reposRecyclerView.setHasFixedSize(true);
        binding.reposRecyclerView.setAdapter(repoRecyclerAdapter);
    }
    @Override
    public void onLoadFinish(PagedList<Repository> repositories) {
        repoRecyclerAdapter.submitList(repositories);
    }

    @Override
    public void showProgressBar() {
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.loadingIndicator.setVisibility(View.GONE);
    }
}
