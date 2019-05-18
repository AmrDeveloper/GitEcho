package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.ActivityStargazerBinding;

public class StargazerActivity extends AppCompatActivity {

    private String username;
    private String repositoryName;
    private ActivityStargazerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_stargazer);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        repositoryName = intent.getStringExtra("repositoryName");

        
    }
}
