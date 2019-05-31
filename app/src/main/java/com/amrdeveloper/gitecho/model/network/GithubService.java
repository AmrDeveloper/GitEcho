package com.amrdeveloper.gitecho.model.network;

import com.amrdeveloper.gitecho.object.Issue;
import com.amrdeveloper.gitecho.object.RepositoriesList;
import com.amrdeveloper.gitecho.object.Repository;
import com.amrdeveloper.gitecho.object.Stargazer;
import com.amrdeveloper.gitecho.object.Account;
import com.amrdeveloper.gitecho.object.UsersList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users/{user}")
    Call<Account> getOneUser(@Path("user") String user);

    @GET("repos/{user}/{repo}")
    Call<Repository> getRepository(
            @Path("user") String username,
            @Path("repo") String repoName);

    @GET("users/{user}/repos")
    Call<List<Repository>> userListRepos(
            @Path("user") String user,
            @Query("page") int pageNum,
            @Query("per_page") int pageSize);

    @GET("search/users")
    Call<UsersList> getUsersList(
            @Query("q") String query,
            @Query("page") int pageNum,
            @Query("per_page") int pageSize);

    @GET("search/repositories")
    Call<RepositoriesList> getRepoList(
            @Query("q") String query,
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

    @GET("repos/{user}/{repo}/issues?state=closed")
    Call<List<Issue>> getRepoIssues(
            @Path("user") String username,
            @Path("repo") String repoName,
            @Query("state") String state,
            @Query("page") int pageNum,
            @Query("per_page") int pageSize
    );
}
