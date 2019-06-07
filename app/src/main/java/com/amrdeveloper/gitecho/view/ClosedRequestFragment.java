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

import com.amrdeveloper.gitecho.databinding.DataListViewBinding;
import com.amrdeveloper.gitecho.model.contract.PullRequestContract;
import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.presenter.PullRequestPresenter;
import com.amrdeveloper.gitecho.model.network.pulls.PullRequestViewModel;
import com.amrdeveloper.gitecho.adapter.PullPagedAdapter;
import com.amrdeveloper.gitecho.object.PullRequest;
import com.amrdeveloper.gitecho.utils.Consts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ClosedRequestFragment
        extends Fragment
        implements PullRequestContract.View {

    private String username;
    private String repositoryName;
    private PullRequestPresenter presenter;
    private DataListViewBinding binding;
    private PullPagedAdapter pullPagedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataListViewBinding.inflate(inflater,container,false);
        setRecyclerViewSettings();

        PullRequestViewModel.setRequestData(username,repositoryName, PullRequest.STATE_CLOSE);
        PullRequestViewModel viewModel = ViewModelProviders.of(this).get(PullRequestViewModel.class);

        presenter = new PullRequestPresenter(this,viewModel,this);
        presenter.startLoadingData();

        return binding.getRoot();
    }

    private void setRecyclerViewSettings() {
        pullPagedAdapter = new PullPagedAdapter(getActivity());
        binding.dataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.dataRecyclerView.setHasFixedSize(true);
        binding.dataRecyclerView.setAdapter(pullPagedAdapter);
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.username = args.getString(Consts.USERNAME,"");
        this.repositoryName = args.getString(Consts.REPOSITORY_NAME,"");
    }

    @Override
    public void onLoadFinish(PagedList<PullRequest> requests) {
        pullPagedAdapter.submitList(requests);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadFinishEvent(LoadFinishEvent<PagedList<PullRequest>> requests){
        onLoadFinish(requests.getResultData());
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
