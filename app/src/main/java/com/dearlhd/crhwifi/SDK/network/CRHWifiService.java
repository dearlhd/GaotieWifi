package com.dearlhd.crhwifi.SDK.network;

import com.dearlhd.crhwifi.SDK.bean.Account;
import com.dearlhd.crhwifi.SDK.bean.City;
import com.dearlhd.crhwifi.SDK.bean.History;
import com.dearlhd.crhwifi.SDK.bean.RecommendBean;
import com.dearlhd.crhwifi.SDK.response.AddHistoryResponse;
import com.dearlhd.crhwifi.SDK.response.HistoryResponse;
import com.dearlhd.crhwifi.SDK.response.NewsResponse;
import com.dearlhd.crhwifi.SDK.response.LoginResponse;
import com.dearlhd.crhwifi.SDK.response.NoBodyEntity;
import com.dearlhd.crhwifi.SDK.response.RecommendResponse;
import com.dearlhd.crhwifi.SDK.response.UserResponse;
import com.dearlhd.crhwifi.SDK.response.WeatherResponse;
import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dearlhd on 2017/12/11.
 */
public interface CRHWifiService {

    /**
     * 登录
     */
    @Headers({"Content-type:application/json"})
    @POST("users")
    Observable<LoginResponse> login(@Body Account account);

    /**
     * 获取用户信息
     */
    @GET("users/{uid}")
    Observable<UserResponse> getUserInfo(@Path("uid") long userId);

    /**
     * 获得新闻列表
     * @return
     */
    @GET("news")
    Observable<NewsResponse> getNewsList();

    /**
     * 添加访问记录
     */
    @Headers({"Content-type:application/json"})
    @POST("histories")
    Observable<AddHistoryResponse> addHistory(@Body History history);

    /**
     * 根据uid获得访问记录
     */
    @GET("histories/{uid}")
    Observable<HistoryResponse> getHistories(@Path("uid") long userId);

    /**
     * 获取天气信息
     */
    @Headers({"Content-type:application/json"})
    @POST("weather")
    Observable<WeatherResponse> getWeather (@Body City city);

    /**
     * 获取推荐列表
     */
    @Headers({"Content-type:application/json"})
    @POST("recommend")
    Observable<RecommendResponse> getRecommendData (@Body RecommendBean recommendBean);

    /**
     * 添加收藏
     */
    @Headers({"Content-type:application/json"})
    @POST("favorite")
    Observable<NoBodyEntity> sendFavorite (@Body JsonObject obj);
}
