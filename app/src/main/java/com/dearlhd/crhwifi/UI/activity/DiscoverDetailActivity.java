package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dearlhd.crhwifi.R;

/**
 * Created by dearlhd on 2017/12/25.
 */
public class DiscoverDetailActivity extends Activity {

    private WebView mWvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_detail);
        initView();
    }

    private void initView () {
        mWvDetail = (WebView) findViewById(R.id.wv_discover_detail);

        WebSettings webSettings = mWvDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        mWvDetail.canGoBack();
        Intent intent = getIntent();
        mWvDetail.loadUrl(intent.getStringExtra("url"));
        mWvDetail.setWebChromeClient(new WebChromeClient());
    }
}
