package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.adapter.RepoRecyclerAdapter;
import com.amrdeveloper.gitecho.databinding.ActivityMainBinding;
import com.amrdeveloper.gitecho.model.MainContract;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;
    private ActivityMainBinding binding;
    private RepoRecyclerAdapter repoRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this, this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        setRecyclerViewSettings();
        setActivityTitle(username);

        presenter.startLoadingData(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.profileMenu) {
            //TODO : Go To ProfileActivity
            Toast.makeText(this, "Open Profile Activity", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void setRecyclerViewSettings() {
        repoRecyclerAdapter = new RepoRecyclerAdapter(this);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecyclerView.setHasFixedSize(true);
        binding.repoRecyclerView.setAdapter(repoRecyclerAdapter);
    }

    private void setActivityTitle(String username) {
        setTitle("@" + username);
    }

    @Override
    public void onLoadingSuccess(List<Repository> repositoryList) {
        repoRecyclerAdapter.updateRecyclerData(repositoryList);
    }

    @Override
    public void onLoadingFailure() {
        Toast.makeText(this, "Loading Error", Toast.LENGTH_SHORT).show();
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
