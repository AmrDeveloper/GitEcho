package com.amrdeveloper.gitecho.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.amrdeveloper.gitecho.databinding.StarListItemBinding;
import com.amrdeveloper.gitecho.object.Stargazer;
import com.squareup.picasso.Picasso;

public class StarRecyclerAdapter {

    class StarViewHolder extends RecyclerView.ViewHolder{

        private StarListItemBinding binding;

        private StarViewHolder(@NonNull StarListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindStargazer(Stargazer stargazer){
            binding.username.setText(stargazer.getUsername());
            Picasso.get().load(stargazer.getAvatarUrl()).into(binding.avatarImg);

        }
    }
}
