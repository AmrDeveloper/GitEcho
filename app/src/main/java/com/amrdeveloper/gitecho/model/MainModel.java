package com.amrdeveloper.gitecho.model;

import android.content.Context;

import com.amrdeveloper.gitecho.object.Repository;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public class MainModel implements MainContract.Model{

    private static final Gson gson = new Gson();

    @Override
    public void loadingDataFromApi(Context context, String url, OnLoadListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray repos = new JSONArray(response);
                        Type listType = new TypeToken<List<Repository>>() {}.getType();
                        List<Repository> repositoryList = gson.fromJson(response, listType);
                        listener.onLoadingSuccess(repositoryList);
                    } catch (JSONException e) {
                        listener.onLoadingFailure();
                    }
                }, error -> {
            listener.onLoadingFailure();
        });
        queue.add(stringRequest);
    }
}
