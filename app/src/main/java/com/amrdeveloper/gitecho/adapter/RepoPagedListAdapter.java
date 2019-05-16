package com.amrdeveloper.gitecho.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.RepoListItemBinding;
import com.amrdeveloper.gitecho.object.Repository;

public class RepoPagedListAdapter extends PagedListAdapter<Repository, RepoPagedListAdapter.RepoViewHolder> {

    private Context context;

    public RepoPagedListAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutID = R.layout.repo_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        RepoListItemBinding binding = DataBindingUtil.inflate(inflater, layoutID, viewGroup, shouldAttachToParentImmediately);
        return new RepoPagedListAdapter.RepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder repoViewHolder, int i) {
        Repository repository = getItem(i);
        if(repository != null){
            repoViewHolder.bingRepository(repository);
        }
    }

    private static DiffUtil.ItemCallback<Repository> DIFF_CALL_BACK = new DiffUtil.ItemCallback<Repository>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repository oldRepo, @NonNull Repository newRepo) {
            return oldRepo.getFullName().equals(newRepo.getFullName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repository oldRepo, @NonNull Repository newRepo) {
            return oldRepo.equals(newRepo);
        }
    };

    class RepoViewHolder extends RecyclerView.ViewHolder {

        private RepoListItemBinding binding;

        private RepoViewHolder(@NonNull RepoListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bingRepository(Repository repository) {
            binding.repoNameTxt.setText(repository.getName());
            binding.repoDescTxt.setText(repository.getDescription());
            binding.repoLangTxt.setText(repository.getMainLanguage());
            binding.repoStarTxt.setText(String.valueOf(repository.getStarNum()));
            binding.repoForkTxt.setText(String.valueOf(repository.getForkNum()));

            boolean isForked = repository.isForked();
            if (isForked) {
                binding.repoIcon.setImageResource(R.drawable.ic_repo_forked);
            }

            boolean isArchived = repository.isArchived();
            if (isArchived) {
                binding.repoArchivedTxt.setText(context.getString(R.string.archived));
                binding.repoArchivedTxt.setVisibility(View.VISIBLE);
            }

        }
    }
}
