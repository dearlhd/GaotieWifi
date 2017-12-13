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
import android.widget.Toast;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.UI.fragment.ConnectFragment;

/**
 * Created by dearlhd on 2016/12/16.
 */
public class HomePageActivity extends FragmentActivity {

    private ConnectFragment mConnectFragment;

    private ImageView mIvTab0;
    private ImageView mIvTab1;
    private ImageView mIvTab2;
    private ImageView mIvTab3;

    private OnBackListener mOnBackListener;

    public interface OnBackListener {
        boolean onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();
        mIvTab0.performClick();
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
            if (mOnBackListener != null) {
                if (mOnBackListener.onBackPressed()) {
                    return true;
                }
            }

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

    public void setOnBackListener(OnBackListener listener) {
        mOnBackListener = listener;
    }

    private void initView() {
        mIvTab0 = (ImageView) findViewById(R.id.iv_tab0);
        mIvTab1 = (ImageView) findViewById(R.id.iv_tab1);
        mIvTab2 = (ImageView) findViewById(R.id.iv_tab2);
        mIvTab3 = (ImageView) findViewById(R.id.iv_tab3);

        mIvTab0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTabIcon();
                //mIvTab0.setImageResource(R.drawable.icon_platform_selected);
                changeFragment(0);
            }
        });

        mIvTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTabIcon();
                //mIvTab1.setImageResource(R.drawable.icon_platform_selected);
                changeFragment(0);
            }
        });

        mIvTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTabIcon();
                //mIvTab2.setImageResource(R.drawable.icon_platform_selected);
                changeFragment(0);
            }
        });

        mIvTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTabIcon();
                //mIvTab3.setImageResource(R.drawable.icon_platform_selected);
                changeFragment(0);
            }
        });
    }

    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);

        switch (index) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                if (mConnectFragment == null) {
                    mConnectFragment = new ConnectFragment();
                    fragmentTransaction.add(R.id.fl_content, mConnectFragment);
                } else {
                    fragmentTransaction.show(mConnectFragment);
                }
                break;
            case 3:

                break;
        }

        fragmentTransaction.commit();
    }

    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (mConnectFragment != null) {
            fragmentTransaction.hide(mConnectFragment);
        }

    }

    private void initTabIcon () {
//        mIvTab0.setImageResource(R.drawable.icon_data_unselected);
//        mIvTab1.setImageResource(R.drawable.icon_statement_unselected);
//        mIvTab2.setImageResource(R.drawable.icon_platform_unselected);
//        mIvTab3.setImageResource(R.drawable.icon_setting_unselected);
    }

}
