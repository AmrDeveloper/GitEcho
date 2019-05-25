package com.amrdeveloper.gitecho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.utils.Consts;

public class RepositoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Intent intent = getIntent();
        String query = intent.getStringExtra(Consts.QUERY);
    }
}