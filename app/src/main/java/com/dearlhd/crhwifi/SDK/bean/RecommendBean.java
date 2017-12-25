package com.dearlhd.crhwifi.SDK.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dearlhd on 2017/12/25.
 */
public class RecommendBean {
    @SerializedName("city_name")
    private String city;
    @SerializedName("user_id")
    private long uid;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
