package com.amrdeveloper.gitecho.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.PullListItemBinding;
import com.amrdeveloper.gitecho.object.PullRequest;

import java.util.ArrayList;
import java.util.List;

public class PullRecyclerAdapter extends RecyclerView.Adapter<PullRecyclerAdapter.PullViewHolder>{

    private Context context;
    private List<PullRequest> pullRequestList;

    public PullRecyclerAdapter(Context context) {
        this.context = context;
        this.pullRequestList = new ArrayList<>();
    }

    public PullRecyclerAdapter(Context context, List<PullRequest> pullRequestList) {
        this.context = context;
        this.pullRequestList = pullRequestList;
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
         PullRequest currentPullRequest = pullRequestList.get(i);
         pullViewHolder.bingPullRequest(currentPullRequest);
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

    public void updateRecyclerData(List<PullRequest> pullRequestList){
        if(pullRequestList != null){
            this.pullRequestList = pullRequestList;
            notifyDataSetChanged();
        }
    }

    class PullViewHolder extends RecyclerView.ViewHolder{

        private PullListItemBinding binding;

        private PullViewHolder(@NonNull PullListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bingPullRequest(PullRequest pullRequest){
            binding.pullTitleTxt.setText(pullRequest.getTitle());
        }
    }
}
