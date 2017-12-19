package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by dearlhd on 2017/12/18.
 */
public class RemindActivity extends Activity {

    private TextView mTvDest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        initView();
    }

    private void initView () {
        mTvDest = (TextView) findViewById(R.id.tv_destination);
        mTvDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = new ArrayList<String>();
                list.add("背景");
                list.add("上海");
                list.add("杭州");

                final OptionPicker picker = new OptionPicker(RemindActivity.this, list);
                picker.setOffset(2);
                picker.setSelectedIndex(1); //默认选中项
                picker.setTextSize(18);
                picker.setCycleDisable(true); //选项不循环滚动

                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        picker.dismiss();
                    }
                });
                picker.show();
            }
        });
    }
}
