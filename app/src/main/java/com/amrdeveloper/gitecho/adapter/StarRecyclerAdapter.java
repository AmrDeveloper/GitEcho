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
import com.amrdeveloper.gitecho.databinding.StarListItemBinding;
import com.amrdeveloper.gitecho.object.Stargazer;
import com.squareup.picasso.Picasso;

public class StarRecyclerAdapter extends PagedListAdapter<Stargazer,StarRecyclerAdapter.StarViewHolder> {

    private Context context;

    public StarRecyclerAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.context = context;
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutID = R.layout.star_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        StarListItemBinding binding = DataBindingUtil.inflate(inflater, layoutID, viewGroup, shouldAttachToParentImmediately);
        return new StarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder starViewHolder, int i) {
        Stargazer stargazer = getItem(i);
        if(stargazer != null){
            starViewHolder.bindStargazer(stargazer);
        }
    }

    private static DiffUtil.ItemCallback<Stargazer> DIFF_CALL_BACK = new DiffUtil.ItemCallback<Stargazer>() {
        @Override
        public boolean areItemsTheSame(@NonNull Stargazer oldStargazer, @NonNull Stargazer newStargazer) {
            return oldStargazer.getUsername().equals(newStargazer.getUsername());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Stargazer oldStargazer, @NonNull Stargazer newStargazer) {
            return oldStargazer.equals(newStargazer);
        }
    };

    class StarViewHolder extends RecyclerView.ViewHolder{

        private StarListItemBinding binding;

        private StarViewHolder(@NonNull StarListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindStargazer(Stargazer stargazer){
            binding.username.setText(stargazer.getUsername());
            Picasso.get().load(stargazer.getAvatarUrl()).fit().into(binding.avatarImg);
        }
    }
}
