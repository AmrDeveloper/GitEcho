package com.amrdeveloper.gitecho.model.network;

import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.object.Stargazer;
import com.amrdeveloper.gitecho.object.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users/{user}")
    Call<User> getOneUser(@Path("user") String user);

    @GET("repos/{user}/{repo}")
    Call<Repository> getRepository(
            @Path("user") String username,
            @Path("repo") String repoName
    );

    @GET("users/{user}/repos")
    Call<List<Repository>> userListRepos(
            @Path("user") String user,
            @Query("page") int pageNum,
            @Query("per_page") int pageSize);

    @GET("repos/{user}/{repo}/stargazers")
    Call<List<Stargazer>> getStarsRepos(
            @Path("user") String username,
            @Path("repo") String repoName,
            @Query("page") int pageNum,
            @Query("per_page") int pageSize);

    @GET("repos/{user}/{repo}/forks")
    Call<List<Repository>> getForksRepos(
            @Path("user") String username,
            @Path("repo") String repoName,
            @Query("page") int pageNum,
            @Query("per_page") int pageSize);
}
