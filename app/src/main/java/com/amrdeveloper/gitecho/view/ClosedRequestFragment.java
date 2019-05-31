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

import com.amrdeveloper.gitecho.PullRequestContract;
import com.amrdeveloper.gitecho.PullRequestPresenter;
import com.amrdeveloper.gitecho.PullRequestViewModel;
import com.amrdeveloper.gitecho.adapter.PullPagedAdapter;
import com.amrdeveloper.gitecho.databinding.RequestListBinding;
import com.amrdeveloper.gitecho.object.PullRequest;
import com.amrdeveloper.gitecho.utils.Consts;

public class ClosedRequestFragment
        extends Fragment
        implements PullRequestContract.View {

    private String username;
    private String repositoryName;
    private PullRequestPresenter presenter;
    private RequestListBinding binding;
    private PullPagedAdapter pullPagedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RequestListBinding.inflate(inflater,container,false);
        setRecyclerViewSettings();

        PullRequestViewModel.setRequestData(username,repositoryName, PullRequest.STATE_OPEN);
        PullRequestViewModel viewModel = ViewModelProviders.of(this).get(PullRequestViewModel.class);

        presenter = new PullRequestPresenter(this,viewModel,this);
        presenter.startLoadingData();

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

    @Override
    public void onLoadFinish(PagedList<PullRequest> issues) {
        pullPagedAdapter.submitList(issues);
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
