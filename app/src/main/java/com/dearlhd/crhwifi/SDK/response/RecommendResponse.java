package com.dearlhd.crhwifi.SDK.response;

import com.dearlhd.crhwifi.SDK.bean.Hotel;
import com.dearlhd.crhwifi.SDK.bean.Restaurant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/25.
 */
public class RecommendResponse {
    @SerializedName("hotel")
    public List<Hotel> hotels;

    @SerializedName("restaurant")
    public List<Restaurant> restaurants;
}
