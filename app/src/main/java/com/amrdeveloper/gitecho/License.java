package com.amrdeveloper.gitecho;

import com.google.gson.annotations.SerializedName;

public class License {

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public License(String key, String name, String url) {
        this.key = key;
        this.name = name;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
