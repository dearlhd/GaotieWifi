package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.History;
import com.dearlhd.crhwifi.SDK.network.CRHWifiApi;
import com.dearlhd.crhwifi.SDK.response.AddHistoryResponse;
import com.dearlhd.crhwifi.SDK.response.NoBodyEntity;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Subscriber;

/**
 * Created by dearlhd on 2017/12/20.
 */
public class NewsDetailActivity extends Activity {

    private ImageView mIvBack;
    private ImageView mIvFavorite;

    private TextView mTvTitle;
    private TextView mTvTime;
    private TextView mTvSource;
    private TextView mTvContent;
    private ImageView mIvNewsImage;

    private Subscriber<NoBodyEntity> mFavoriteSubscriber;
    private Subscriber<AddHistoryResponse> mHistorySubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        sendAccessRecord();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvFavorite = (ImageView) findViewById(R.id.iv_favorite);
        mTvTitle = (TextView) findViewById(R.id.tv_news_title);
        mTvTime = (TextView) findViewById(R.id.tv_news_time);
        mTvSource = (TextView) findViewById(R.id.tv_news_source);
        mTvContent = (TextView) findViewById(R.id.tv_news_content);
        mIvNewsImage = (ImageView) findViewById(R.id.iv_news_image);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mIvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFavorite();
            }
        });

        Intent intent = getIntent();
        mTvTitle.setText(intent.getStringExtra("title"));
        mTvTime.setText(intent.getStringExtra("time"));
        mTvSource.setText(intent.getStringExtra("source"));
        mTvContent.setText(intent.getStringExtra("content"));

        byte[] bitmapArray = decodeBase64(intent.getStringExtra("image"));
        mIvNewsImage.setImageBitmap(BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length));
    }

    private void sendFavorite () {
        mFavoriteSubscriber = new Subscriber<NoBodyEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(NoBodyEntity noBodyEntity) {
                mIvFavorite.setImageResource(R.drawable.ic_star_full);
            }
        };

        SQLiteHelper helper = new SQLiteHelper();
        CRHWifiApi.getInstance().sendFavorite(mFavoriteSubscriber, helper.getUid(), getIntent().getLongExtra("news_id", 0));
    }

    private void sendAccessRecord () {
        mHistorySubscriber = new Subscriber<AddHistoryResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(AddHistoryResponse response) {

            }
        };

        History history = new History();
        history.setUserId(5);
        history.setNewsId(getIntent().getLongExtra("news_id", 1));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        history.setBrowseTime(date);
        CRHWifiApi.getInstance().addHistory(mHistorySubscriber, history);
    }

    private byte[] decodeBase64 (String base) {
        return Base64.decode(base.getBytes(), Base64.DEFAULT);
    }
}
