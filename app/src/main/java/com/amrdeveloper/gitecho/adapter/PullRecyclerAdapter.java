package com.amrdeveloper.gitecho.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.PullListItemBinding;
import com.amrdeveloper.gitecho.object.PullRequest;

public class PullRecyclerAdapter extends PagedListAdapter<PullRequest,PullRecyclerAdapter.PullViewHolder> {

    private Context context;

    protected PullRecyclerAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.context = context;
    }

    @NonNull
    @Override
    public PullViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        int layoutID = R.layout.pull_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        PullListItemBinding binding = DataBindingUtil.inflate(inflater, layoutID, parent, shouldAttachToParentImmediately);
        return new PullViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PullViewHolder pullViewHolder, int i) {
        PullRequest currentPullRequest = getItem(i);
        if (currentPullRequest != null) {
            pullViewHolder.bingPullRequest(currentPullRequest);
        }
    }

    private static DiffUtil.ItemCallback<PullRequest> DIFF_CALL_BACK = new DiffUtil.ItemCallback<PullRequest>() {
        @Override
        public boolean areItemsTheSame(@NonNull PullRequest oldRequest, @NonNull PullRequest newRequest) {
            return oldRequest.getPullUrl().equals(newRequest);
        }

        @Override
        public boolean areContentsTheSame(@NonNull PullRequest oldRequest, @NonNull PullRequest newRequest) {
            return oldRequest.equals(newRequest);
        }
    };

    class PullViewHolder extends RecyclerView.ViewHolder {

        private PullListItemBinding binding;

        private PullViewHolder(@NonNull PullListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bingPullRequest(PullRequest pullRequest) {
            binding.pullTitleTxt.setText(pullRequest.getTitle());
            //TODO : create info like github with format
            //TODO : #<Number> <opened/closed> on <Time> by <creator>
            binding.issueInfo.setText(pullRequest.getState());
            switch (pullRequest.getPullRequestType()) {
                case PullRequest.STATE_OPEN: {
                    binding.pullIcon.setImageResource(R.drawable.ic_pull_request);
                    break;
                }
                case PullRequest.STATE_CLOSE: {
                    binding.pullIcon.setImageResource(R.drawable.ic_close_pull_request);
                    break;
                }
                case PullRequest.STATE_MERGE: {
                    binding.pullIcon.setImageResource(R.drawable.ic_git_merge);
                    break;
                }
            }
        }
    }
}
