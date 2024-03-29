package com.amrdeveloper.gitecho.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.UserListItemBinding;
import com.amrdeveloper.gitecho.object.Type;
import com.amrdeveloper.gitecho.object.User;
import com.amrdeveloper.gitecho.utils.Consts;
import com.amrdeveloper.gitecho.view.ProfileActivity;
import com.squareup.picasso.Picasso;

public class UserPagedAdapter extends PagedListAdapter<User, UserPagedAdapter.UserViewHolder> {

    private Context context;

    public UserPagedAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        int layoutID = R.layout.user_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        UserListItemBinding binding = DataBindingUtil.inflate(inflater, layoutID, parent, shouldAttachToParentImmediately);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        User currentUser = getItem(i);
        if(currentUser != null){
            userViewHolder.bingUser(currentUser);
        }
    }

    private static DiffUtil.ItemCallback<User> DIFF_CALL_BACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldUser, @NonNull User newUser) {
            return oldUser.getName().equals(newUser.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldUser, @NonNull User newUser) {
            return oldUser.equals(newUser);
        }
    };

    class UserViewHolder extends RecyclerView.ViewHolder {

        private UserListItemBinding binding;

        private UserViewHolder(@NonNull UserListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bingUser(User user) {
            binding.userNameTxt.setText(user.getName());
            binding.userTypeTxt.setText(user.getUserType().name());
            Picasso.get().load(user.getAvatarUrl()).fit().into(binding.userAvatarImg);

            binding.userCardView.setOnClickListener(v -> {
                Type userType = user.getUserType();
                if (userType == Type.USER){
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra(Consts.USERNAME,user.getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
