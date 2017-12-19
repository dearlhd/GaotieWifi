package com.dearlhd.crhwifi.SDK.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dearlhd on 2017/12/19.
 */
public class LoginResponse {
    @SerializedName("result")
    public int result;

    @SerializedName("uid")
    public int uid;
}
