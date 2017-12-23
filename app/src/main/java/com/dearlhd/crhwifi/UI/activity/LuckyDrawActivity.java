package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.UI.view.LuckyPan.LuckPanLayout;
import com.dearlhd.crhwifi.UI.view.LuckyPan.RotatePan;

/**
 * Created by dearlhd on 2017/12/21.
 */
public class LuckyDrawActivity extends Activity {

    private LuckPanLayout mLayoutLuckyPan;
    private RotatePan mViewRotatePan;
    private ImageView mIvGo;

    private String[] strs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_draw);
        initView();
    }

    private void initView() {
        mLayoutLuckyPan = (LuckPanLayout) findViewById(R.id.layout_lucky_pan);
        mViewRotatePan = (RotatePan) findViewById(R.id.view_rotate_pan);
        mIvGo = (ImageView) findViewById(R.id.iv_go);

        initLuckyPan();
    }

    private void initLuckyPan () {
        strs = getResources().getStringArray(R.array.names);
        mLayoutLuckyPan.setAnimationEndListener(new LuckPanLayout.AnimationEndListener() {
            @Override
            public void endAnimation(int position) {
                Toast.makeText(LuckyDrawActivity.this, "恭喜你获得了" + strs[position], Toast.LENGTH_SHORT).show();
            }
        });

        mIvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotation();
            }
        });
    }

    private void rotation(){
        mLayoutLuckyPan.rotate(-1,100);
    }
}
