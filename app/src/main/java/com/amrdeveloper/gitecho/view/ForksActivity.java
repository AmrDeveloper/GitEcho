package com.amrdeveloper.gitecho.view;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.amrdeveloper.gitecho.model.ForksContract;
import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.presenter.ForksPresenter;
import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.adapter.RepoPagedListAdapter;
import com.amrdeveloper.gitecho.databinding.ActivityForksBinding;
import com.amrdeveloper.gitecho.model.network.forks.ForksViewModel;
import com.amrdeveloper.gitecho.object.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForksActivity extends AppCompatActivity implements ForksContract.View {

    private String username;
    private String repositoryName;
    private ActivityForksBinding binding;
    private ForksContract.Presenter presenter;

    private RepoPagedListAdapter repoRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forks);
        setRecyclerViewSettings();

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        repositoryName = intent.getStringExtra("repoName");

        ForksViewModel.setRequestData(username,repositoryName);
        ForksViewModel itemViewModel = ViewModelProviders.of(this).get(ForksViewModel.class);

        presenter = new ForksPresenter(this,itemViewModel,this);
        presenter.startLoadingData(repositoryName);

    }

    private void setRecyclerViewSettings() {
        repoRecyclerAdapter = new RepoPagedListAdapter(this);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecyclerView.setHasFixedSize(true);
        binding.repoRecyclerView.setAdapter(repoRecyclerAdapter);
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
