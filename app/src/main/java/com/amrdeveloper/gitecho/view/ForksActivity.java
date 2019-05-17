package com.amrdeveloper.gitecho.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.ActivityForksBinding;

public class ForksActivity extends AppCompatActivity {

    private ActivityForksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forks);
    }
}
