package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amrdeveloper.gitecho.model.events.LoadFinishEvent;
import com.amrdeveloper.gitecho.presenter.ProfilePresenter;
import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.databinding.ActivityProfileBinding;
import com.amrdeveloper.gitecho.model.contract.ProfileContract;
import com.amrdeveloper.gitecho.object.Account;
import com.amrdeveloper.gitecho.utils.Consts;
import com.amrdeveloper.gitecho.utils.FormatUtils;
import com.amrdeveloper.gitecho.utils.Session;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity
        implements ProfileContract.View {

    private ProfilePresenter presenter;
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        Intent intent = getIntent();
        String username = intent.getStringExtra(Consts.USERNAME);

        setActivityTitle(username);

        presenter = new ProfilePresenter(this);
        presenter.loadUserInformation(username);
    }

    private void setActivityTitle(String username) {
        setTitle(String.format(FormatUtils.USERNAME,username));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.logoutMenu) {
            showLogoutDialog();
        }
        return true;
    }

    @Override
    public void onLoadFinish(Account user) {
        binding.gistTxt.setText(String.format(Locale.ENGLISH,FormatUtils.GISTS,user.getGistsNumber()));
        binding.reposTxt.setText(String.format(Locale.ENGLISH,FormatUtils.REPOSITORIES,user.getReposNumber()));
        binding.followersTxt.setText(String.format(Locale.ENGLISH,FormatUtils.FOLLOWERS,user.getFollowersNum()));
        binding.followingTxt.setText(String.format(Locale.ENGLISH,FormatUtils.FOLLOWING,user.getFollowingNum()));
        Picasso.get().load(user.getAvatarUrl()).fit().into(binding.avatarImg);

        //Bind Name
        String name = user.getName();
        if(name != null)
            binding.nameTxt.setText(name);
        else
            binding.nameTxt.setVisibility(View.GONE);

        //Bind Company Name
        String company = user.getCompany();
        if (company != null)
            binding.companyTxt.setText(company);
        else
            binding.companyTxt.setVisibility(View.GONE);

        //Bind Company
        String location = user.getLocation();
        if (location != null)
            binding.locationTxt.setText(location);
        else
            binding.locationTxt.setVisibility(View.GONE);

        //Bing Email
        String email = user.getEmail();
        if (email != null)
            binding.mailText.setText(email);
        else
            binding.mailText.setVisibility(View.GONE);

        //Bing Biro
        String bio = user.getBiography();
        if (bio != null)
            binding.bioTxt.setText(bio);
        else
            binding.bioTxt.setVisibility(View.GONE);

        binding.reposTxt.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(Consts.USERNAME,user.getUsername());
            startActivity(intent);
        });
    }

    private void showLogoutDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("You want logout?");
        builder1.setCancelable(true);

        builder1.setPositiveButton("Yes",(dialog, id) -> userLogout());
        builder1.setNegativeButton( "No",(dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void userLogout(){
        Session session = new Session(this);
        session.logOut();

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadFinishEvent(LoadFinishEvent<Account> loadFinish){
        onLoadFinish(loadFinish.getResultData());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
