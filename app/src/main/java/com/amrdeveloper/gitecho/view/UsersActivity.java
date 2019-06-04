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
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.object.Stargazer;
import com.amrdeveloper.gitecho.presenter.UsersPresenter;
import com.amrdeveloper.gitecho.model.network.users.UsersViewModel;
import com.amrdeveloper.gitecho.adapter.UserPagedAdapter;
import com.amrdeveloper.gitecho.model.contract.UsersContract;
import com.amrdeveloper.gitecho.databinding.ActivityUsersBinding;
import com.amrdeveloper.gitecho.object.User;
import com.amrdeveloper.gitecho.utils.Consts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UsersActivity extends AppCompatActivity implements UsersContract.View {

    private ActivityUsersBinding binding;
    private UserPagedAdapter userPagedAdapter;
    private UsersContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users);

        Intent intent = getIntent();
        String query = intent.getStringExtra(Consts.QUERY);

        setRecyclerViewSettings();

        UsersViewModel.setSearchQuery(query);
        UsersViewModel viewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        presenter = new UsersPresenter(this,viewModel,this);
        presenter.startLoadingData(query);
    }

    private void setRecyclerViewSettings() {
        userPagedAdapter = new UserPagedAdapter(this);
        binding.usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.usersRecyclerView.setHasFixedSize(true);
        binding.usersRecyclerView.setAdapter(userPagedAdapter);
    }

    @Override
    public void onLoadFinish(PagedList<User> users) {
        userPagedAdapter.submitList(users);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadFinishEvent(LoadFinishEvent<PagedList<User>> users){
        onLoadFinish(users.getResultData());
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
