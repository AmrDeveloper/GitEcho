package com.amrdeveloper.gitecho;

import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.object.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users/{user}")
    Call<User> getOneUser(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repository>> userListRepos(
            @Path("user") String user
            , @Query("page") int pageNum,
            @Query("per_page") int pageSize);
}
