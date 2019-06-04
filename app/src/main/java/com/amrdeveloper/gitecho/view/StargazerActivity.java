package com.amrdeveloper.gitecho.view;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.adapter.StarPagedAdapter;
import com.amrdeveloper.gitecho.databinding.ActivityStargazerBinding;
import com.amrdeveloper.gitecho.model.contract.StarsContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.model.network.stars.StarsViewModel;
import com.amrdeveloper.gitecho.object.Stargazer;
import com.amrdeveloper.gitecho.presenter.StarsPresenter;
import com.amrdeveloper.gitecho.utils.Consts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StargazerActivity extends AppCompatActivity implements StarsContract.View {

    private String username;
    private String repositoryName;
    private StarsPresenter presenter;
    private ActivityStargazerBinding binding;
    private StarPagedAdapter starPagedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_stargazer);
        setRecyclerViewSettings();

        Intent intent = getIntent();
        username = intent.getStringExtra(Consts.USERNAME);
        repositoryName = intent.getStringExtra(Consts.REPOSITORY_NAME);

        StarsViewModel.setRequestData(username,repositoryName);
        StarsViewModel itemViewModel = ViewModelProviders.of(this).get(StarsViewModel.class);

        presenter = new StarsPresenter(this,itemViewModel,this);
        presenter.startLoadingData();
    }

    private void setRecyclerViewSettings() {
        starPagedAdapter = new StarPagedAdapter(this);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecyclerView.setHasFixedSize(true);
        binding.repoRecyclerView.setAdapter(starPagedAdapter);
    }

    @Override
    public void onLoadFinish(PagedList<Stargazer> stargazers) {
        starPagedAdapter.submitList(stargazers);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadFinishEvent(LoadFinishEvent<PagedList<Stargazer>> stargazers){
        onLoadFinish(stargazers.getResultData());
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
