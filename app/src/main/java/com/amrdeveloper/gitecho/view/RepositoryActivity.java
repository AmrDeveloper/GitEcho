package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amrdeveloper.gitecho.utils.Consts;
import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.model.contract.RepositoryContract;
import com.amrdeveloper.gitecho.presenter.RepositoryPresenter;
import com.amrdeveloper.gitecho.databinding.ActivityRepositoryBinding;
import com.amrdeveloper.gitecho.object.License;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.utils.DateUtils;
import com.amrdeveloper.gitecho.utils.FormatUtils;
import com.amrdeveloper.gitecho.utils.UpdateFormatter;

import java.util.Date;
import java.util.Locale;

public class RepositoryActivity extends AppCompatActivity implements RepositoryContract.View {

    private ActivityRepositoryBinding binding;
    private RepositoryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository);

        Intent intent = getIntent();
        String username = intent.getStringExtra(Consts.USERNAME);
        String repositoryName = intent.getStringExtra(Consts.REPOSITORY_NAME);

        presenter = new RepositoryPresenter(this);
        presenter.loadRepositoryInformation(username, repositoryName);
    }

    @Override
    public void onLoadFinish(Repository repository) {
        binding.repoNameTxt.setText(repository.getFullName());
        binding.repoDescTxt.setText(repository.getDescription());
        binding.repoLangTxt.setText(repository.getMainLanguage());
        binding.repoStarTxt.setText(String.format(Locale.ENGLISH,FormatUtils.STARS,repository.getStarNum()));
        binding.repoForkTxt.setText(String.format(Locale.ENGLISH,FormatUtils.FORKS,repository.getForkNum()));

        License license = repository.getLicense();
        if (license != null) {
            binding.repoLicenseTxt.setText(license.getName());
        }else{
            binding.repoLicenseTxt.setVisibility(View.GONE);
        }

        String createdAtDate = repository.getCreatedAt();
        String updatedAtDate = repository.getUpdatedAt();

        Date createdDate = DateUtils.formatStringToDate(createdAtDate);
        String createdAt = DateUtils.formatDateToString(createdDate);
        String updatedAt = UpdateFormatter.getUpdatedFromTime(updatedAtDate);

        binding.repoCreatedAtTxt.setText(String.format(FormatUtils.CREATED_AT,createdAt));
        binding.repoUpdatedFromTxt.setText(updatedAt);

        binding.repoStarTxt.setOnClickListener(v -> {
            Intent intent = new Intent(RepositoryActivity.this, StargazerActivity.class);
            intent.putExtra(Consts.USERNAME,repository.getRepoOwner().getOwnerName());
            intent.putExtra(Consts.REPOSITORY_NAME,repository.getName());
            startActivity(intent);
        });

        binding.repoForkTxt.setOnClickListener(v -> {
            Intent intent = new Intent(RepositoryActivity.this, ForksActivity.class);
            intent.putExtra(Consts.USERNAME,repository.getRepoOwner().getOwnerName());
            intent.putExtra(Consts.REPOSITORY_NAME,repository.getName());
            startActivity(intent);
        });

        binding.repoPullRequests.setOnClickListener(v -> {
            Intent intent = new Intent(RepositoryActivity.this, PullRequestActivity.class);
            intent.putExtra(Consts.USERNAME,repository.getRepoOwner().getOwnerName());
            intent.putExtra(Consts.REPOSITORY_NAME,repository.getName());
            startActivity(intent);
        });

        binding.repoIssues.setOnClickListener(v -> {
            Intent intent = new Intent(RepositoryActivity.this, IssuesActivity.class);
            intent.putExtra(Consts.USERNAME,repository.getRepoOwner().getOwnerName());
            intent.putExtra(Consts.REPOSITORY_NAME,repository.getName());
            startActivity(intent);
        });
    }
}
