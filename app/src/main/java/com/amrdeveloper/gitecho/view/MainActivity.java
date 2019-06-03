package com.amrdeveloper.gitecho.view;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.amrdeveloper.gitecho.databinding.MultiSearchDialogBinding;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.object.Type;
import com.amrdeveloper.gitecho.receiver.NetworkReceiver;
import com.amrdeveloper.gitecho.receiver.OnNetworkListener;
import com.amrdeveloper.gitecho.utils.Consts;
import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.adapter.RepoPagedAdapter;
import com.amrdeveloper.gitecho.model.network.repo.RepoViewModel;
import com.amrdeveloper.gitecho.databinding.ActivityMainBinding;
import com.amrdeveloper.gitecho.model.contract.MainContract;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.presenter.MainPresenter;
import com.amrdeveloper.gitecho.utils.FormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity
        extends AppCompatActivity
        implements MainContract.View, OnNetworkListener {

    private String username;
    private MainPresenter presenter;
    private ActivityMainBinding binding;
    private RepoPagedAdapter repoRecyclerAdapter;
    private NetworkReceiver networkReceiver;

    private Snackbar onlineSnackBar;
    private Snackbar offlineSnackBar;
    private boolean isStateChangedBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Intent intent = getIntent();
        username = intent.getStringExtra(Consts.USERNAME);

        setRecyclerViewSettings();
        setActivityTitle(username);

        RepoViewModel.setUsername(username);
        RepoViewModel itemViewModel = ViewModelProviders.of(this).get(RepoViewModel.class);

        presenter = new MainPresenter(this, itemViewModel, this);
        presenter.startLoadingData(username);

        networkReceiver = new NetworkReceiver(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        getMenuInflater().inflate(R.menu.profile_menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.searchMenu);

        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint(getString(R.string.filter_result));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            case R.id.profileMenu: {
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra(Consts.USERNAME, username);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    private void setRecyclerViewSettings() {
        repoRecyclerAdapter = new RepoPagedAdapter(this);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecyclerView.setHasFixedSize(true);
        binding.repoRecyclerView.setAdapter(repoRecyclerAdapter);
    }

    private void setActivityTitle(String username) {
        setTitle(String.format(FormatUtils.USERNAME, username));
    }

    @Override
    public void onLoadFinish(PagedList<Repository> repositories) {
        repoRecyclerAdapter.submitList(repositories);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadFinishEvent(LoadFinishEvent<PagedList<Repository>> repositories){
        onLoadFinish(repositories.getResultData());
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

    public void showSearchDialog(View view) {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlide;
        MultiSearchDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.multi_search_dialog, null, false);
        dialog.setContentView(binding.getRoot());

        binding.searchButton.setOnClickListener(v -> {
            String searchQuery = binding.searchQuery.getText().toString();

            if (searchQuery.isEmpty()) {
                Toast.makeText(this, "Invalid Search Query", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] searchTypeArray = getResources().getStringArray(R.array.search_type);
            int searchTypePosition = binding.searchType.getSelectedItemPosition();
            String searchTypeStr = searchTypeArray[searchTypePosition].toUpperCase();
            Type searchType = Type.valueOf(searchTypeStr);

            Class typeActivity = null;

            switch (searchType) {
                case USER: {
                    typeActivity = UsersActivity.class;
                    break;
                }
                case REPOSITORY: {
                    typeActivity = RepositoriesActivity.class;
                    break;
                }
            }

            Intent intent = new Intent(this, typeActivity);
            intent.putExtra(Consts.QUERY, searchQuery);
            startActivity(intent);
        });

        dialog.show();
    }

    @Override
    public void onInternetConnected() {
        if(isStateChangedBefore){
            if(onlineSnackBar == null){
                final View view = getWindow().getDecorView().getRootView();
                onlineSnackBar = Snackbar.make(view, R.string.online, Snackbar.LENGTH_SHORT);
                onlineSnackBar.getView().setBackgroundResource(R.color.green);
            }
            if(offlineSnackBar != null && offlineSnackBar.isShown()){
                offlineSnackBar.dismiss();
            }
            onlineSnackBar.show();
        }else{
            isStateChangedBefore = true;
        }
    }

    @Override
    public void onInternetDisConnected() {
        if(offlineSnackBar == null){
            final View view = getWindow().getDecorView().getRootView();
            offlineSnackBar = Snackbar.make(view, R.string.offline, Snackbar.LENGTH_INDEFINITE);
            offlineSnackBar.getView().setBackgroundResource(R.color.red);
        }
        offlineSnackBar.show();
        isStateChangedBefore = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkReceiver);
        EventBus.getDefault().unregister(this);
    }
}
