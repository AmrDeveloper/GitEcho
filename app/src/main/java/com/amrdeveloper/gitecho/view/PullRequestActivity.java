package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.ActivityPullRequestBinding;
import com.amrdeveloper.gitecho.utils.Consts;

public class PullRequestActivity extends AppCompatActivity {

    private String username;
    private String repositoryName;
    private Bundle repositoryBundleData;
    private ActivityPullRequestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pull_request);
        binding.requestBottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        Intent intent = getIntent();
        username = intent.getStringExtra(Consts.USERNAME);
        repositoryName = intent.getStringExtra(Consts.REPOSITORY_NAME);

        repositoryBundleData = new Bundle();
        repositoryBundleData.putString(Consts.USERNAME, username);
        repositoryBundleData.putString(Consts.REPOSITORY_NAME, repositoryName);

        Fragment openRequestFragment = new OpenRequestFragment();
        openRequestFragment.setArguments(repositoryBundleData);
        getSupportFragmentManager().beginTransaction().replace(R.id.requestFragmentContainer,openRequestFragment).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = menuItem -> {
        int menuID = menuItem.getItemId();
        Fragment selectedFragment = null;

        switch (menuID){
            case R.id.openIssues:{
                selectedFragment = new OpenRequestFragment();
                selectedFragment.setArguments(repositoryBundleData);
                break;
            }
            case R.id.closedIssues:{
                selectedFragment = new ClosedRequestFragment();
                selectedFragment.setArguments(repositoryBundleData);
                break;
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.requestFragmentContainer,selectedFragment).commit();
        return false;
    };
}
