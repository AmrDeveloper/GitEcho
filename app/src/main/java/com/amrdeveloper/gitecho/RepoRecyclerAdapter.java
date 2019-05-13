package com.amrdeveloper.gitecho;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.databinding.RepoListItemBinding;
import com.amrdeveloper.gitecho.object.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepoRecyclerAdapter extends RecyclerView.Adapter<RepoRecyclerAdapter.RepoViewHolder>{

    private Context context;
    private List<Repository> repositoryList;

    public RepoRecyclerAdapter(Context context){
        this.context = context;
        this.repositoryList = new ArrayList<>();
    }

    public RepoRecyclerAdapter(Context context, List<Repository> repositoryList){
        this.context = context;
        this.repositoryList = repositoryList;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        int layoutID = R.layout.repo_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        RepoListItemBinding binding = DataBindingUtil.inflate(inflater,layoutID,parent,shouldAttachToParentImmediately);
        return new RepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder repoViewHolder, int i) {
        Repository currentRepo = repositoryList.get(i);
        repoViewHolder.bingRepository(currentRepo);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public void updateRecyclerData(List<Repository> repositoryList){
        if(repositoryList != null){
            this.repositoryList = repositoryList;
            notifyDataSetChanged();
        }
    }

    class RepoViewHolder extends RecyclerView.ViewHolder{

        private RepoListItemBinding binding;

        private RepoViewHolder(@NonNull RepoListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bingRepository(Repository repository){
            binding.repoNameTxt.setText(repository.getName());
            binding.repoDescTxt.setText(repository.getDescription());
            binding.repoLangTxt.setText(repository.getMainLanguage());
            binding.repoStarTxt.setText(String.valueOf(repository.getStarNum()));
            binding.repoForkTxt.setText(String.valueOf(repository.getForkNum()));

            boolean isArchived = repository.isArchived();
            if(isArchived){
                binding.repoArchivedTxt.setText(context.getString(R.string.archived));
                binding.repoArchivedTxt.setVisibility(View.VISIBLE);
            }else{
                binding.repoArchivedTxt.setVisibility(View.INVISIBLE);
            }
        }
    }
}
