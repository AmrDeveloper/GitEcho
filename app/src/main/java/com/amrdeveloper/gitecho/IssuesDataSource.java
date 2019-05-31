package com.amrdeveloper.gitecho;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.Issue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesDataSource extends PageKeyedDataSource<Integer, Issue> {

    private String username;
    private String repositoryName;
    private String issueType;

    private static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 25;
    public static final int INITIAL_LOAD_SIZE = 50;

    public IssuesDataSource(String username, String repositoryName, String issueType) {
         this.username = username;
         this.repositoryName = repositoryName;
         this.issueType = issueType;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Issue> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoIssues(username,repositoryName,issueType,PAGE_NUM,PAGE_SIZE)
                .enqueue(new Callback<List<Issue>>() {
                    @Override
                    public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                        if(response.body() != null){
                            List<Issue> issueList = response.body();
                            if(issueList.size() == PAGE_SIZE){
                                callback.onResult(issueList,null,PAGE_NUM + 1);
                            }else{
                                callback.onResult(issueList,null,null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Issue>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Issue> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoIssues(username,repositoryName,issueType,params.key,PAGE_SIZE)
                .enqueue(new Callback<List<Issue>>() {
                    @Override
                    public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Issue>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Issue> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoIssues(username,repositoryName,issueType,params.key,PAGE_SIZE)
                .enqueue(new Callback<List<Issue>>() {
                    @Override
                    public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                        if(response.body() != null){
                            Integer key = response.body().size() == PAGE_SIZE ? params.key + 1 : null;
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Issue>> call, Throwable t) {

                    }
                });
    }
}
