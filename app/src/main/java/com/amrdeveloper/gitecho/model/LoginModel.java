package com.amrdeveloper.gitecho.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginModel implements LoginContract.Model {

    @Override
    public boolean isUsernameValid(String username) {
        return !username.isEmpty();
    }

    @Override
    public void makeLoginRequest(Context context, String url, OnLoginListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject result = new JSONObject(response);
                        String login = result.getString("login");
                        listener.onLoginSuccess(login);
                    } catch (JSONException e) {
                        listener.onLoginFailure();
                    }
                }, error -> {
            listener.onLoginFailure();
        });
        queue.add(stringRequest);
    }
}
