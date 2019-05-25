package com.amrdeveloper.gitecho;

import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amrdeveloper.gitecho.databinding.ActivityOrganizationsBinding;
import com.amrdeveloper.gitecho.object.Organiztion;
import com.amrdeveloper.gitecho.utils.Consts;

public class OrganizationsActivity extends AppCompatActivity implements OrganizationsContract.View {

    private ActivityOrganizationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_organizations);

        Intent intent = getIntent();
        String query = intent.getStringExtra(Consts.QUERY);
    }

    @Override
    public void onLoadFinish(PagedList<Organiztion> organizations) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
