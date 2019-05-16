package com.amrdeveloper.gitecho.model.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit retrofit;
    private static RetrofitClient mInstance;
    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    synchronized public static RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public GithubService getGithubService(){
        return retrofit.create(GithubService.class);
    }
}
