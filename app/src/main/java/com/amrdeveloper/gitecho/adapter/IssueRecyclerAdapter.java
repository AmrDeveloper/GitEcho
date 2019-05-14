package com.amrdeveloper.gitecho.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.IssueListItemBinding;
import com.amrdeveloper.gitecho.object.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRecyclerAdapter extends RecyclerView.Adapter<IssueRecyclerAdapter.IssueViewHolder> {

    private Context context;
    private List<Issue> issueList;

    public IssueRecyclerAdapter(Context context) {
        this.context = context;
        this.issueList = new ArrayList<>();
    }

    public IssueRecyclerAdapter(Context context, List<Issue> issueList) {
        this.context = context;
        this.issueList = issueList;
    }

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        int layoutID = R.layout.issue_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        IssueListItemBinding binding = DataBindingUtil.inflate(inflater, layoutID, parent, shouldAttachToParentImmediately);
        return new IssueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder issueViewHolder, int i) {
        Issue currentIssue = issueList.get(i);
        issueViewHolder.bindIssue(currentIssue);
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    private void updateRecyclerData(List<Issue> issueList) {
        if (issueList != null) {
            this.issueList = issueList;
            notifyDataSetChanged();
        }
    }

    class IssueViewHolder extends RecyclerView.ViewHolder {

        private IssueListItemBinding binding;

        private IssueViewHolder(@NonNull IssueListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindIssue(Issue issue) {
            //TODO : create info like github with format
            //TODO : #<Number> <opened/closed> on <Time> by <creator>
            int issueIcon = (issue.getIssueType().equals(Issue.OPENED))
                    ? R.drawable.ic_issue_opened : R.drawable.ic_issue_closed;
            binding.issueIcon.setImageResource(issueIcon);
            binding.issueInfo.setText(issue.getState());
            binding.issueTitleTxt.setText(issue.getTitle());
        }
    }
}
