package com.amrdeveloper.gitecho.view;

import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.amrdeveloper.gitecho.model.contract.OrganizationsContract;
import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.ActivityOrganizationsBinding;
import com.amrdeveloper.gitecho.object.Organiztion;
import com.amrdeveloper.gitecho.utils.Consts;

public class OrganizationsActivity extends AppCompatActivity implements OrganizationsContract.View {

    private ActivityOrganizationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_organizations);

        Intent intent = getIntent();
        String query = intent.getStringExtra(Consts.QUERY);

        setRecyclerViewSettings();
    }

    private void setRecyclerViewSettings() {
        //repoRecyclerAdapter = new RepoPagedAdapter(this);
        binding.orgsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.orgsRecyclerView.setHasFixedSize(true);
        //binding.orgsRecyclerView.setAdapter(repoRecyclerAdapter);
    }

    @Override
    public void onLoadFinish(PagedList<Organiztion> organizations) {
        //TODO : Submit List to PagedAdapter
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
