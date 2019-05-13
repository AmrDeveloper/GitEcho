package com.amrdeveloper.gitecho.object;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("login")
    private String ownerName;

    @SerializedName("type")
    private String ownerType;

    public Owner(String ownerName, String ownerType) {
        this.ownerName = ownerName;
        this.ownerType = ownerType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerType() {
        return ownerType;
    }
}
