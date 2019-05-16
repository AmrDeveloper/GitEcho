package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private String username;
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        
        setActivityTitle(username);
    }

    private void setActivityTitle(String username) {
        setTitle("@" + username);
    }
}
