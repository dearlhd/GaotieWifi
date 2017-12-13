package com.dearlhd.crhwifi.SDK.network;

import com.dearlhd.crhwifi.SDK.bean.Account;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by dearlhd on 2017/12/11.
 */
public interface CRHWifiService {

    /**
     * 登录
     */
    @Headers({"Content-type:application/json"})
    @POST("CRHWifi/account/login")
    Observable<Integer> login(@Body Account account);
}
