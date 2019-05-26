package com.amrdeveloper.gitecho.model.network.users;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.amrdeveloper.gitecho.model.network.RetrofitClient;
import com.amrdeveloper.gitecho.object.User;
import com.amrdeveloper.gitecho.object.UsersList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersDataSource extends PageKeyedDataSource<Integer, User> {

    private String query;
    private static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 25;
    public static final int INITIAL_LOAD_SIZE = 50;

    public UsersDataSource(String query){
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, User> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getUsersList(query,PAGE_NUM,PAGE_SIZE)
                .enqueue(new Callback<UsersList>() {
                    @Override
                    public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                        if(response.body() != null){
                            List<User> repoList = response.body().getUsersList();
                            if(repoList.size() == PAGE_SIZE){
                                callback.onResult(repoList,null,PAGE_NUM + 1);
                            }else{
                                callback.onResult(repoList,null,null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersList> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getUsersList(query,params.key,PAGE_SIZE)
                .enqueue(new Callback<UsersList>() {
                    @Override
                    public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getUsersList(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersList> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {
        RetrofitClient.getInstance()
                .getGithubService()
                .getUsersList(query,params.key,PAGE_SIZE)
                .enqueue(new Callback<UsersList>() {
                    @Override
                    public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                        if(response.body() != null){
                            Integer key = response.body().getUsersList().size() == PAGE_SIZE ? params.key + 1 : null;
                            callback.onResult(response.body().getUsersList(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersList> call, Throwable t) {

                    }
                });
    }
}
