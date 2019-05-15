package com.amrdeveloper.gitecho;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.amrdeveloper.gitecho.object.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDataSource extends PageKeyedDataSource<Integer, Repository> {

    private String username;
    private static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 25;

    public RepoDataSource(String username){
       this.username = username;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Repository> callback) {
          RetrofitClient.getInstance()
                  .getGithubService()
                  .userListRepos(username,PAGE_NUM,PAGE_SIZE)
                  .enqueue(new Callback<List<Repository>>() {
                      @Override
                      public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                            if(response.body() != null){
                                List<Repository> repoList = response.body();
                                if(repoList.size() == PAGE_SIZE){
                                    callback.onResult(repoList,null,PAGE_NUM + 1);
                                }else{
                                    callback.onResult(repoList,null,null);
                                }
                            }
                      }

                      @Override
                      public void onFailure(Call<List<Repository>> call, Throwable t) {

                      }
                  });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repository> callback) {
          RetrofitClient.getInstance()
                  .getGithubService()
                  .userListRepos(username,params.key,PAGE_SIZE)
                  .enqueue(new Callback<List<Repository>>() {
                      @Override
                      public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                          if(response.body() != null){
                              Integer key = (params.key > 1) ? params.key - 1 : null;
                              callback.onResult(response.body(), key);
                          }
                      }

                      @Override
                      public void onFailure(Call<List<Repository>> call, Throwable t) {

                      }
                  });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repository> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .userListRepos(username,params.key,PAGE_SIZE)
                .enqueue(new Callback<List<Repository>>() {
                    @Override
                    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                        if(response.body() != null){
                            Integer key = response.body().size() == PAGE_SIZE ? params.key + 1 : null;
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repository>> call, Throwable t) {

                    }
                });
    }
}
