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
import com.amrdeveloper.gitecho.databinding.OrgListItemBinding;
import com.amrdeveloper.gitecho.object.Organization;
import com.squareup.picasso.Picasso;

public class OrgPagedAdapter extends PagedListAdapter<Organization, OrgPagedAdapter.OrgViewHolder> {

    private Context context;

    protected OrgPagedAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.context = context;
    }

    @NonNull
    @Override
    public OrgViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutID = R.layout.org_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;
        OrgListItemBinding binding = DataBindingUtil.inflate(inflater, layoutID, viewGroup, shouldAttachToParentImmediately);
        return new OrgViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrgViewHolder orgViewHolder, int i) {
        Organization currentOrg = getItem(i);
        if (currentOrg != null) {
            orgViewHolder.bindOrganization(currentOrg);
        }
    }

    private static DiffUtil.ItemCallback<Organization> DIFF_CALL_BACK = new DiffUtil.ItemCallback<Organization>() {
        @Override
        public boolean areItemsTheSame(@NonNull Organization oldOrg, @NonNull Organization newOrg) {
            return oldOrg.getName().equals(newOrg.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Organization oldOrg, @NonNull Organization newOrg) {
            return oldOrg.equals(newOrg);
        }
    };

    class OrgViewHolder extends RecyclerView.ViewHolder {

        private OrgListItemBinding binding;

        private OrgViewHolder(OrgListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindOrganization(Organization organization) {
            binding.orgNameTxt.setText(organization.getName());
            Picasso.get().load(organization.getAvatarUrl()).fit().into(binding.orgAvatarImg);
        }
    }
}
