package com.amrdeveloper.gitecho.model.network.pulls;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestDataSource extends PageKeyedDataSource<Integer, PullRequest> {

    private String username;
    private String repositoryName;
    private String pullRequestType;
    private static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 25;
    public static final int INITIAL_LOAD_SIZE = 50;

    public PullRequestDataSource(String username,String repositoryName,String requestType){
        this.username = username;
        this.repositoryName = repositoryName;
        this.pullRequestType = requestType;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, PullRequest> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoPullRequests(username,repositoryName,pullRequestType,PAGE_NUM,PAGE_SIZE)
                .enqueue(new Callback<List<PullRequest>>() {
                    @Override
                    public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                        if(response.body() != null){
                            List<PullRequest> requestList = response.body();
                            if(requestList.size() == PAGE_SIZE){
                                callback.onResult(requestList,null,PAGE_NUM + 1);
                            }else{
                                callback.onResult(requestList,null,null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PullRequest>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PullRequest> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoPullRequests(username,repositoryName,pullRequestType,params.key,PAGE_SIZE)
                .enqueue(new Callback<List<PullRequest>>() {
                    @Override
                    public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PullRequest>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PullRequest> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoPullRequests(username,repositoryName,pullRequestType,params.key,PAGE_SIZE)
                .enqueue(new Callback<List<PullRequest>>() {
                    @Override
                    public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                        if(response.body() != null){
                            Integer key = response.body().size() == PAGE_SIZE ? params.key + 1 : null;
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PullRequest>> call, Throwable t) {

                    }
                });
    }
}
