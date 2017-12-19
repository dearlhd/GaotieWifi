package com.dearlhd.crhwifi.SDK.response;

import com.dearlhd.crhwifi.SDK.bean.News;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/19.
 */
public class NewsResponse {
    @SerializedName("data")
    public List<News> newses;
}
