package com.dearlhd.crhwifi.UI.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.UI.fragment.ConnectFragment;
import com.dearlhd.crhwifi.UI.fragment.DiscoveryFragment;
import com.dearlhd.crhwifi.UI.fragment.MyselfFragment;
import com.dearlhd.crhwifi.UI.fragment.NewsFragment;

/**
 * Created by dearlhd on 2016/12/16.
 */
public class HomePageActivity extends FragmentActivity {

    private ConnectFragment mConnectFragment;
    private NewsFragment mNewsFragment;
    private DiscoveryFragment mDiscoveryFragment;
    private MyselfFragment mMyselfFragment;

    private LinearLayout mLlTab0;
    private LinearLayout mLlTab1;
    private LinearLayout mLlTab2;
    private LinearLayout mLlTab3;

    private TextView mTvTab0;
    private TextView mTvTab1;
    private TextView mTvTab2;
    private TextView mTvTab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();
        mLlTab0.performClick();
    }

    /* ---------------------- 下面是针对按下返回键的逻辑 ------------------------*/
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(HomePageActivity.this, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                moveTaskToBack(true);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        mLlTab0 = (LinearLayout) findViewById(R.id.ll_tab0);
        mLlTab1 = (LinearLayout) findViewById(R.id.ll_tab1);
        mLlTab2 = (LinearLayout) findViewById(R.id.ll_tab2);
        mLlTab3 = (LinearLayout) findViewById(R.id.ll_tab3);

        mTvTab0 = (TextView) findViewById(R.id.tv_tab0);
        mTvTab1 = (TextView) findViewById(R.id.tv_tab1);
        mTvTab2 = (TextView) findViewById(R.id.tv_tab2);
        mTvTab3 = (TextView) findViewById(R.id.tv_tab3);

        mLlTab0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTab();
                mTvTab0.setTextColor(getResources().getColor(R.color.colorRedText));
                changeFragment(0);
            }
        });

        mLlTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTab();
                mTvTab1.setTextColor(getResources().getColor(R.color.colorRedText));
                changeFragment(1);
            }
        });

        mLlTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTab();
                mTvTab2.setTextColor(getResources().getColor(R.color.colorRedText));
                changeFragment(2);
            }
        });

        mLlTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTab();
                mTvTab3.setTextColor(getResources().getColor(R.color.colorRedText));
                changeFragment(3);
            }
        });
    }

    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);

        switch (index) {
            case 0:
                if (mConnectFragment == null) {
                    mConnectFragment = new ConnectFragment();
                    fragmentTransaction.add(R.id.fl_content, mConnectFragment);
                } else {
                    fragmentTransaction.show(mConnectFragment);
                }
                break;
            case 1:
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                    fragmentTransaction.add(R.id.fl_content, mNewsFragment);
                } else {
                    fragmentTransaction.show(mNewsFragment);
                }
                break;
            case 2:
                if (mDiscoveryFragment == null) {
                    mDiscoveryFragment = new DiscoveryFragment();
                    fragmentTransaction.add(R.id.fl_content, mDiscoveryFragment);
                } else {
                    fragmentTransaction.show(mDiscoveryFragment);
                }
                break;
            case 3:
                if (mMyselfFragment == null) {
                    mMyselfFragment = new MyselfFragment();
                    fragmentTransaction.add(R.id.fl_content, mMyselfFragment);
                } else {
                    fragmentTransaction.show(mMyselfFragment);
                }
                break;
        }

        fragmentTransaction.commit();
    }

    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (mConnectFragment != null) {
            fragmentTransaction.hide(mConnectFragment);
        }

        if (mNewsFragment != null) {
            fragmentTransaction.hide(mNewsFragment);
        }

        if (mMyselfFragment != null) {
            fragmentTransaction.hide(mMyselfFragment);
        }

        if (mDiscoveryFragment != null) {
            fragmentTransaction.hide(mDiscoveryFragment);
        }
    }

    private void initTab () {
        mTvTab0.setTextColor(getResources().getColor(R.color.colorBlackText));
        mTvTab1.setTextColor(getResources().getColor(R.color.colorBlackText));
        mTvTab2.setTextColor(getResources().getColor(R.color.colorBlackText));
        mTvTab3.setTextColor(getResources().getColor(R.color.colorBlackText));
    }

}
