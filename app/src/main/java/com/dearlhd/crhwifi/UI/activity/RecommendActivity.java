package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.dearlhd.crhwifi.R;

/**
 * Created by dearlhd on 2017/12/21.
 */
public class RecommendActivity extends Activity {

    enum STATE { NONE, FOOD, HOTEL};

    private STATE mState = STATE.NONE;

    private ImageView mIvBack;
    private WebView mWvRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_hotel);
        checkState();
        initView();
    }

    private void checkState () {
        Intent intent = getIntent();
        if (intent.getStringExtra("state").equals("FOOD")) {
            mState = STATE.FOOD;
        } else if (intent.getStringExtra("state").equals("HOTEL")) {
            mState = STATE.HOTEL;
        }
    }

    private void initView () {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mWvRecommend = (WebView) findViewById(R.id.wv_recommend);

        WebSettings webSettings = mWvRecommend.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        mWvRecommend.canGoBack();
        if (mState == STATE.FOOD) {
            mWvRecommend.loadUrl("https://m.dianping.com/shoplist/1/d/1/c/10/s/s_-1?from=m_nav_1_meishi");
        } else if (mState == STATE.HOTEL) {
            mWvRecommend.loadUrl("https://m.dianping.com/awp/h5/hotel-dp/list/list.html?cityid=1&from=m_nav_3_jiudian");
        }
        mWvRecommend.setWebChromeClient(new WebChromeClient());
    }
}
