package com.dearlhd.crhwifi.SDK.network;

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

    public static final String BASEURL = "http://115.159.78.97:8080/";

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

    public CRHWifiApi getInstance() {
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
//    public void login(Subscriber<Integer> subscriber, Account account) {
//        mService.login(account)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
}
