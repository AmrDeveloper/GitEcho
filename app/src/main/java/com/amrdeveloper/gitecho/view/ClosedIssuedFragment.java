package com.amrdeveloper.gitecho.view;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.model.contract.IssuesContract;
import com.amrdeveloper.gitecho.presenter.IssuesPresenter;
import com.amrdeveloper.gitecho.model.network.issues.IssuesViewModel;
import com.amrdeveloper.gitecho.adapter.IssuePagedAdapter;
import com.amrdeveloper.gitecho.databinding.IssuesListBinding;
import com.amrdeveloper.gitecho.object.Issue;
import com.amrdeveloper.gitecho.utils.Consts;

public class ClosedIssuedFragment
        extends Fragment
        implements IssuesContract.View {

    private String username;
    private String repositoryName;
    private IssuesPresenter presenter;
    private IssuesListBinding binding;
    private IssuePagedAdapter issuePagedAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = IssuesListBinding.inflate(inflater,container,false);
        setRecyclerViewSettings();

        IssuesViewModel.setRequestData(username,repositoryName,Issue.STATE_CLOSE);
        IssuesViewModel viewModel = ViewModelProviders.of(this).get(IssuesViewModel.class);

        presenter = new IssuesPresenter(this,viewModel,this);
        presenter.startLoadingData();

        return binding.getRoot();
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.username = args.getString(Consts.USERNAME,"");
        this.repositoryName = args.getString(Consts.REPOSITORY_NAME,"");
    }

    private void setRecyclerViewSettings() {
        issuePagedAdapter = new IssuePagedAdapter(getActivity());
        binding.issuesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.issuesRecyclerView.setHasFixedSize(true);
        binding.issuesRecyclerView.setAdapter(issuePagedAdapter);
    }

    @Override
    public void onLoadFinish(PagedList<Issue> issues) {
        issuePagedAdapter.submitList(issues);
    }

    @Override
    public void showProgressBar() {
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.loadingIndicator.setVisibility(View.GONE);
    }
}
