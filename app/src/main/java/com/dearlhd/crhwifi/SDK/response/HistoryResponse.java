package com.dearlhd.crhwifi.SDK.response;

import com.dearlhd.crhwifi.SDK.bean.History;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/19.
 */
public class HistoryResponse {
    @SerializedName("histories")
    public List<History> histories;
}
