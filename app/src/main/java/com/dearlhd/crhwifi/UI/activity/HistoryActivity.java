package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.History;
import com.dearlhd.crhwifi.SDK.network.CRHWifiApi;
import com.dearlhd.crhwifi.SDK.response.HistoryResponse;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;
import com.dearlhd.crhwifi.UI.adapter.HistoryAdapter;

import java.util.List;

import rx.Subscriber;

/**
 * Created by dearlhd on 2017/12/20.
 */
public class HistoryActivity extends Activity {

    private ListView mLvHistories;
    private Subscriber<HistoryResponse> mHistorySubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
    }

    private void initView() {
        mLvHistories = (ListView) findViewById(R.id.lv_histories);

        mHistorySubscriber = new Subscriber<HistoryResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(HistoryResponse historyResponse) {
                List<History> histories = historyResponse.histories;
                HistoryAdapter adapter = new HistoryAdapter(HistoryActivity.this, histories);
                mLvHistories.setAdapter(adapter);
            }
        };

        SQLiteHelper helper = new SQLiteHelper();
        CRHWifiApi.getInstance().getHistories(mHistorySubscriber, helper.getUid());
    }
}
