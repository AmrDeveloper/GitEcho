package com.amrdeveloper.gitecho;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.utils.Consts;

public class OpenIssuesFragment extends Fragment {

    private String username;
    private String repositoryName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.issue_list_item,container,false);
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.username = args.getString(Consts.USERNAME,"");
        this.repositoryName = args.getString(Consts.REPOSITORY_NAME,"");
    }
}
