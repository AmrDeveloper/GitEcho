package com.amrdeveloper.gitecho.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.adapter.IssuePagedAdapter;
import com.amrdeveloper.gitecho.adapter.PullPagedAdapter;
import com.amrdeveloper.gitecho.databinding.RequestListBinding;
import com.amrdeveloper.gitecho.utils.Consts;

public class ClosedRequestFragment extends Fragment {

    private String username;
    private String repositoryName;
    private RequestListBinding binding;
    private PullPagedAdapter pullPagedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RequestListBinding.inflate(inflater,container,false);
        setRecyclerViewSettings();
        return binding.getRoot();
    }

    private void setRecyclerViewSettings() {
        pullPagedAdapter = new PullPagedAdapter(getActivity());
        binding.requestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.requestRecyclerView.setHasFixedSize(true);
        binding.requestRecyclerView.setAdapter(pullPagedAdapter);
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.username = args.getString(Consts.USERNAME,"");
        this.repositoryName = args.getString(Consts.REPOSITORY_NAME,"");
    }
}
