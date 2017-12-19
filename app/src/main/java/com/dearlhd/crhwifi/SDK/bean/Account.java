package com.dearlhd.crhwifi.SDK.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dearlhd on 2016/12/27.
 */
public class Account {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
