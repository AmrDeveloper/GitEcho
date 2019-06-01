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
import com.amrdeveloper.gitecho.databinding.IssueListItemBinding;
import com.amrdeveloper.gitecho.object.Issue;
import com.amrdeveloper.gitecho.utils.FormatUtils;
import com.amrdeveloper.gitecho.utils.UpdateFormatter;

import java.util.Locale;

public class IssuePagedAdapter extends PagedListAdapter<Issue, IssuePagedAdapter.IssueViewHolder> {

    private Context context;

    public IssuePagedAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.context = context;
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
        Issue currentIssue = getItem(i);
        if(currentIssue != null) {
            issueViewHolder.bindIssue(currentIssue);
        }
    }

    private static DiffUtil.ItemCallback<Issue> DIFF_CALL_BACK = new DiffUtil.ItemCallback<Issue>() {
        @Override
        public boolean areItemsTheSame(@NonNull Issue oldIssue, @NonNull Issue newIssue) {
            return oldIssue.getIssueUrl().equals(newIssue);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Issue oldIssue, @NonNull Issue newIssue) {
            return oldIssue.equals(newIssue);
        }
    };

    class IssueViewHolder extends RecyclerView.ViewHolder {

        private IssueListItemBinding binding;

        private IssueViewHolder(@NonNull IssueListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindIssue(Issue issue) {
            //TODO : create info like github with format
            //TODO : #<Number> <opene/closed> on <Time> by <creator>
            int issueIcon = (issue.getState().equals(Issue.STATE_OPEN))
                    ? R.drawable.ic_issue_opened : R.drawable.ic_issue_closed;
            String issueState = issue.getState();
            int issueNum = issue.getIssueNumber();
            String creator = issue.getCreator().getUsername();
            String date;
            if(issueState.equals(Issue.STATE_OPEN)){
                date = issue.getCreatedAt();
            }else{
                date = issue.getClosedAt();
            }
            String updateFrom = UpdateFormatter.getUpdatedFromTime(date);
            String info = String.format(
                    Locale.ENGLISH,
                    FormatUtils.REQUEST_ISSUE_FORMAT,
                    issueNum,issueState,updateFrom,creator);
            binding.issueIcon.setImageResource(issueIcon);
            binding.issueTitleTxt.setText(issue.getTitle());
            binding.issueInfo.setText(info);
        }
    }
}
