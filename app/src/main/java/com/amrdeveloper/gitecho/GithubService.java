package com.amrdeveloper.gitecho;

import com.amrdeveloper.gitecho.object.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    @GET("users/{user}/repos")
    Call<List<Repository>> listRepos(@Path("user") String user);
}
