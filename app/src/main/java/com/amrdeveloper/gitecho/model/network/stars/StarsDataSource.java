package com.amrdeveloper.gitecho.model.network.stars;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.Stargazer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarsDataSource extends PageKeyedDataSource<Integer, Stargazer> {

    private String username;
    private String repositoryName;
    private static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 25;

    public StarsDataSource(String username,String repositoryName){
        this.username = username;
        this.repositoryName = repositoryName;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Stargazer> callback) {
         RetrofitClient.getInstance()
                 .getGithubService()
                 .getStarsRepos(username,repositoryName,PAGE_NUM,PAGE_SIZE)
                 .enqueue(new Callback<List<Stargazer>>() {
                     @Override
                     public void onResponse(Call<List<Stargazer>> call, Response<List<Stargazer>> response) {
                         if(response.body() != null){
                             List<Stargazer> repoList = response.body();
                             if(repoList.size() == PAGE_SIZE){
                                 callback.onResult(repoList,null,PAGE_NUM + 1);
                             }else{
                                 callback.onResult(repoList,null,null);
                             }
                         }
                     }

                     @Override
                     public void onFailure(Call<List<Stargazer>> call, Throwable t) {

                     }
                 });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Stargazer> callback) {
         RetrofitClient.getInstance()
                 .getGithubService()
                 .getStarsRepos(username,repositoryName,params.key,PAGE_SIZE)
                 .enqueue(new Callback<List<Stargazer>>() {
                     @Override
                     public void onResponse(Call<List<Stargazer>> call, Response<List<Stargazer>> response) {
                         if(response.body() != null){
                             Integer key = (params.key > 1) ? params.key - 1 : null;
                             callback.onResult(response.body(), key);
                         }
                     }

                     @Override
                     public void onFailure(Call<List<Stargazer>> call, Throwable t) {

                     }
                 });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Stargazer> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getStarsRepos(username,repositoryName,params.key,PAGE_SIZE)
                .enqueue(new Callback<List<Stargazer>>() {
                    @Override
                    public void onResponse(Call<List<Stargazer>> call, Response<List<Stargazer>> response) {
                        if(response.body() != null){
                            Integer key = response.body().size() == PAGE_SIZE ? params.key + 1 : null;
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Stargazer>> call, Throwable t) {

                    }
                });
    }
}
