package com.dearlhd.crhwifi.SDK.response;

import com.dearlhd.crhwifi.SDK.bean.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/22.
 */
public class WeatherResponse {
    @SerializedName("city")
    public String city;

    @SerializedName("data")
    public WeatherList forecast;

    public class WeatherList {
        @SerializedName("forecast")
        public List<Weather> weatherList;
    }
}
