package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Hotel;
import com.dearlhd.crhwifi.SDK.bean.RecommendBean;
import com.dearlhd.crhwifi.SDK.bean.Restaurant;
import com.dearlhd.crhwifi.SDK.network.CRHWifiApi;
import com.dearlhd.crhwifi.SDK.response.RecommendResponse;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;
import com.dearlhd.crhwifi.UI.adapter.HotelAdapter;
import com.dearlhd.crhwifi.UI.adapter.RestaurantAdapter;
import com.dearlhd.crhwifi.UI.view.PullToRefreshListView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by dearlhd on 2017/12/21.
 */
public class RecommendActivity extends Activity {

    enum STATE {NONE, FOOD, HOTEL}

    ;

    private STATE mState = STATE.NONE;

    private ImageView mIvBack;
    private TextView mTvPageTitle;
    private PullToRefreshListView mLvRecommendList;

    private Subscriber<RecommendResponse> mRecommendSubscriber;

    private WebView mWvRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_hotel);
        checkState();
        initView();
        mLvRecommendList.onRefresh();
    }

    private void checkState() {
        Intent intent = getIntent();
        if (intent.getStringExtra("state").equals("FOOD")) {
            mState = STATE.FOOD;
        } else if (intent.getStringExtra("state").equals("HOTEL")) {
            mState = STATE.HOTEL;
        }
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mTvPageTitle = (TextView) findViewById(R.id.tv_recommend_page_title);

        mLvRecommendList = (PullToRefreshListView) findViewById(R.id.lv_recommend);
        mLvRecommendList.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRecommendData();
            }

            @Override
            public void onLoadMore() {
                mLvRecommendList.onLoadMoreComplete(false);
            }
        });

//        mWvRecommend = (WebView) findViewById(R.id.wv_recommend);
//
//        WebSettings webSettings = mWvRecommend.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDomStorageEnabled(true);
//        mWvRecommend.canGoBack();
//        if (mState == STATE.FOOD) {
//            mWvRecommend.loadUrl("https://m.dianping.com/shoplist/1/d/1/c/10/s/s_-1?from=m_nav_1_meishi");
//        } else if (mState == STATE.HOTEL) {
//            mWvRecommend.loadUrl("https://m.dianping.com/awp/h5/hotel-dp/list/list.html?cityid=1&from=m_nav_3_jiudian");
//        }
//        mWvRecommend.setWebChromeClient(new WebChromeClient());
    }

    private void getRecommendData() {
        mRecommendSubscriber = new Subscriber<RecommendResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mLvRecommendList.onRefreshComplete();
            }

            @Override
            public void onNext(RecommendResponse recommendResponse) {
                if (mState == STATE.FOOD) {
                    mTvPageTitle.setText("餐饮推荐");
                    List<Restaurant> restaurants = recommendResponse.restaurants;
                    RestaurantAdapter adapter = new RestaurantAdapter(RecommendActivity.this, restaurants);
                    mLvRecommendList.setAdapter(adapter);
                } else if (mState == STATE.HOTEL) {
                    mTvPageTitle.setText("酒店推荐");
                    List<Hotel> hotels = recommendResponse.hotels;
                    HotelAdapter adapter = new HotelAdapter(RecommendActivity.this, hotels);
                    mLvRecommendList.setAdapter(adapter);
                }

                mLvRecommendList.onRefreshComplete();
            }
        };

        RecommendBean bean = new RecommendBean();
        SQLiteHelper helper = new SQLiteHelper();
        String dest = "上海";
        if (helper.getJourney() != null) {
            dest = helper.getJourney().getDestination();
        }
        bean.setCity(dest);
        bean.setUid(helper.getUid());
        CRHWifiApi.getInstance().getRecommendData(mRecommendSubscriber, bean);
    }
}
