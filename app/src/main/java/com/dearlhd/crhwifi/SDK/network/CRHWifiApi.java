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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dearlhd on 2017/12/11.
 */
public class CRHWifiApi {

    public static final String BASEURL = "http://202.120.40.111:25000/";

    private static volatile CRHWifiApi mCRHWifiApi;

    private static Retrofit mRetrofit;
    private static CRHWifiService mService;

    private CRHWifiApi () {
        if (mService == null) {
            if (mRetrofit == null) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                        .build();

                mRetrofit = new Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .client(okHttpClient)
                        .addConverterFactory(NoBodyConvertFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
            mService = mRetrofit.create(CRHWifiService.class);
        }
    }

    public static CRHWifiApi getInstance() {
        if (mCRHWifiApi == null) {
            // 保证单例
            synchronized (CRHWifiApi.class) {
                if (mCRHWifiApi == null) {
                    mCRHWifiApi = new CRHWifiApi();
                }
            }
        }

        return mCRHWifiApi;
    }

    /**
     * 登录
     *
     * @param subscriber 监听者对象
     */
    public void login(Subscriber<LoginResponse> subscriber, Account account) {
        mService.login(account)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取用户信息
     * @param subscriber
     * @param userId
     */
    public void getUserInfo(Subscriber<UserResponse> subscriber, long userId) {
        mService.getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获得新闻列表
     */
    public void getNewsList (Subscriber<NewsResponse> subscriber) {
        mService.getNewsList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 添加访问记录
     */
    public void addHistory (Subscriber<AddHistoryResponse> subscriber, History history) {
        mService.addHistory(history)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获得访问历史
     * @param subscriber
     * @param userId
     */
    public void getHistories (Subscriber<HistoryResponse> subscriber, long userId) {
        mService.getHistories(userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取天气信息
     */
    public void getWeather (Subscriber<WeatherResponse> subscriber, City city) {
        mService.getWeather(city)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取推荐信息（酒店、餐饮）
     */
    public void getRecommendData (Subscriber<RecommendResponse> subscriber, RecommendBean bean) {
        mService.getRecommendData(bean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 添加收藏
     */
    public void sendFavorite (Subscriber<NoBodyEntity> subscriber, long uid, long newsId) {
        JsonObject object = new JsonObject();
        object.addProperty("user_id", uid);
        object.addProperty("poi_id", newsId);
        mService.sendFavorite(object)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 认证上网
     */
    public void verify (Subscriber<NoBodyEntity> subscriber) {
        mService.verify()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
