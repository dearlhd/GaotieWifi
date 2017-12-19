package com.dearlhd.crhwifi.SDK.response;

import com.dearlhd.crhwifi.SDK.bean.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dearlhd on 2017/12/19.
 */
public class UserResponse {
    @SerializedName("result")
    public int result;

    @SerializedName("user_info")
    public User user;
}
