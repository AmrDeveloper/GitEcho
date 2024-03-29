package com.amrdeveloper.gitecho.model.network.repos;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.RepositoriesList;
import com.amrdeveloper.gitecho.object.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposDataSource extends PageKeyedDataSource<Integer, Repository> {

    private String query;
    private static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 25;
    public static final int INITIAL_LOAD_SIZE = 50;

    public ReposDataSource(String query){
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Repository> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoList(query,PAGE_NUM,PAGE_SIZE)
                .enqueue(new Callback<RepositoriesList>() {
                    @Override
                    public void onResponse(Call<RepositoriesList> call, Response<RepositoriesList> response) {
                        if(response.body() != null){
                            List<Repository> repoList = response.body().getRepositoryList();
                            if(repoList.size() == PAGE_SIZE){
                                callback.onResult(repoList,null,PAGE_NUM + 1);
                            }else{
                                callback.onResult(repoList,null,null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RepositoriesList> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repository> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoList(query,params.key,PAGE_SIZE)
                .enqueue(new Callback<RepositoriesList>() {
                    @Override
                    public void onResponse(Call<RepositoriesList> call, Response<RepositoriesList> response) {
                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getRepositoryList(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<RepositoriesList> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repository> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getRepoList(query,params.key,PAGE_SIZE)
                .enqueue(new Callback<RepositoriesList>() {
                    @Override
                    public void onResponse(Call<RepositoriesList> call, Response<RepositoriesList> response) {
                        if(response.body() != null){
                            Integer key = response.body().getRepositoryList().size() == PAGE_SIZE ? params.key + 1 : null;
                            callback.onResult(response.body().getRepositoryList(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<RepositoriesList> call, Throwable t) {

                    }
                });
    }
}
