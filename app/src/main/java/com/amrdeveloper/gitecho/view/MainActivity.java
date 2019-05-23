package com.amrdeveloper.gitecho.view;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Toast;

import com.amrdeveloper.gitecho.databinding.MultiSearchDialogBinding;
import com.amrdeveloper.gitecho.utils.Consts;
import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.adapter.RepoPagedListAdapter;
import com.amrdeveloper.gitecho.model.network.main.RepoViewModel;
import com.amrdeveloper.gitecho.databinding.ActivityMainBinding;
import com.amrdeveloper.gitecho.model.contract.MainContract;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.presenter.MainPresenter;
import com.amrdeveloper.gitecho.utils.FormatUtils;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private String username;
    private MainPresenter presenter;
    private ActivityMainBinding binding;
    private RepoPagedListAdapter repoRecyclerAdapter;

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

        presenter = new MainPresenter(this,itemViewModel,this);
        presenter.startLoadingData(username);
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
        switch (menuId){
            case R.id.profileMenu:{
                Intent intent = new Intent(this,ProfileActivity.class);
                intent.putExtra(Consts.USERNAME,username);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    private void setRecyclerViewSettings() {
        repoRecyclerAdapter = new RepoPagedListAdapter(this);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecyclerView.setHasFixedSize(true);
        binding.repoRecyclerView.setAdapter(repoRecyclerAdapter);
    }

    private void setActivityTitle(String username) {
        setTitle(String.format(FormatUtils.USERNAME,username));
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

    public void showSearchDialog(View view){
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlide;
        MultiSearchDialogBinding binding = DataBindingUtil.setContentView(this,R.layout.multi_search_dialog);

        binding.searchButton.setOnClickListener(v -> {
            String searchQuery = binding.searchQuery.getText().toString();

            if(searchQuery.isEmpty()){
                Toast.makeText(this, "Invalid Search Query", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] searchTypeArray = getResources().getStringArray(R.array.search_type);
            int searchTypePosition = binding.searchType.getSelectedItemPosition();

            Toast.makeText(this, "Start Search", Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }
}
