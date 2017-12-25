package com.dearlhd.crhwifi.UI.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Journey;
import com.dearlhd.crhwifi.SDK.util.CityParser;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;
import com.dearlhd.crhwifi.UI.activity.RecommendActivity;
import com.dearlhd.crhwifi.UI.activity.WeatherActivity;

import java.util.List;
import java.util.Random;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class ConnectFragment extends Fragment {

    private View mRoot;

    private TextView mTvWifi;

    private LinearLayout mLlSetDest;
    private TextView mTvDestination;
    private TextView mTvCurrentStation;
    private TextView mTvArrivalTime;
    private ImageView mIvAd;

    private LinearLayout mLlWeather;
    private LinearLayout mLlHotel;
    private LinearLayout mLlFood;
    private LinearLayout mLlTask;

    private Journey mJourney;
    private PopupWindow mTaskWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_connect, container, false);
            initView();
        }

        ViewGroup parent = (ViewGroup) mRoot.getParent();
        if (parent != null) {
            parent.removeView(mRoot);
        }

        return mRoot;
    }

    @Override
    public void onStart() {
        super.onStart();
        SQLiteHelper helper = new SQLiteHelper();
        mJourney = helper.getJourney();
        if (mJourney != null) {
            mTvDestination.setText(mJourney.getDestination());
            mTvCurrentStation.setText(mJourney.getCurrentStation());
            mTvArrivalTime.setText(mJourney.getArrivalTime());
        } else {
            mTvDestination.setText("请选择");
            mTvCurrentStation.setText("--");
            mTvArrivalTime.setText("--");
            mJourney = new Journey();
        }
    }

    private void initView () {
        mTvWifi = (TextView) mRoot.findViewById(R.id.tv_wifi_name);

        mLlSetDest = (LinearLayout) mRoot.findViewById(R.id.ll_set_destination);
        mTvDestination = (TextView) mRoot.findViewById(R.id.tv_destination);
        mTvCurrentStation = (TextView) mRoot.findViewById(R.id.tv_current_station);
        mTvArrivalTime = (TextView) mRoot.findViewById(R.id.tv_arrival_time);
        mIvAd = (ImageView) mRoot.findViewById(R.id.iv_ad);
        setAd();

        mLlWeather = (LinearLayout) mRoot.findViewById(R.id.ll_weather);
        mLlWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                startActivity(intent);
            }
        });
        mLlFood = (LinearLayout) mRoot.findViewById(R.id.ll_food_recommend);
        mLlFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RecommendActivity.class);
                intent.putExtra("state", "FOOD");
                startActivity(intent);
            }
        });
        mLlHotel = (LinearLayout) mRoot.findViewById(R.id.ll_hotel_recommend);
        mLlHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RecommendActivity.class);
                intent.putExtra("state", "HOTEL");
                startActivity(intent);
            }
        });
        mLlTask = (LinearLayout) mRoot.findViewById(R.id.ll_task);
        initTaskWindow();
        mLlTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 0.5f;
                getActivity().getWindow().setAttributes(lp);
                mTaskWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        });

        mTvWifi.setText(getWifiInfo().getSSID());

        mLlSetDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> cities = CityParser.getCities(getContext());
                cities.add(0, "请选择");
                final OptionPicker picker = new OptionPicker(getActivity(), cities);
                picker.setOffset(2);
                picker.setSelectedIndex(0); //默认选中项
                picker.setTextSize(18);
                picker.setTextColor(getResources().getColor(R.color.colorBlackText));
                picker.setCycleDisable(true); //选项不循环滚动

                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        if (index != 0) {
                            mJourney.setDestination(item);
                            mJourney.setArrivalTime("18:38");
                            mTvDestination.setText(item);
                            mTvCurrentStation.setText("苏州");
                            mTvArrivalTime.setText("18:38");
                            SQLiteHelper helper = new SQLiteHelper();
                            helper.setJourney(mJourney);
                        }
                    }
                });
                picker.show();
            }
        });
    }

    private void setAd () {
        Random random = new Random();
        int a = random.nextInt(100) % 7 + 1;
        switch (a) {
            case 1:
                mIvAd.setImageResource(R.drawable.img_ad1);
                break;
            case 2:
                mIvAd.setImageResource(R.drawable.img_ad2);
                break;
            case 3:
                mIvAd.setImageResource(R.drawable.img_ad3);
                break;
            case 4:
                mIvAd.setImageResource(R.drawable.img_ad4);
                break;
            case 5:
                mIvAd.setImageResource(R.drawable.img_ad5);
                break;
            case 6:
                mIvAd.setImageResource(R.drawable.img_ad6);
                break;
            case 7:
                mIvAd.setImageResource(R.drawable.img_ad7);
                break;
        }
    }

    private WifiInfo getWifiInfo () {
        WifiManager wifi_service = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifi_service.getConnectionInfo();
        return wifiInfo;
    }

    private void initTaskWindow() {
        View taskView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_task, null);
        mTaskWindow = new PopupWindow(getContext());
        mTaskWindow.setContentView(taskView);
        mTaskWindow.setWidth(800);
        mTaskWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mTaskWindow.setOutsideTouchable(true);
        mTaskWindow.setFocusable(true);
        mTaskWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_round_corner));
        mTaskWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        ImageView ivClose = (ImageView) taskView.findViewById(R.id.iv_close);
        TextView tvToFinish = (TextView) taskView.findViewById(R.id.tv_to_finish);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTaskWindow.dismiss();
            }
        });
        tvToFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTaskWindow.dismiss();
            }
        });


    }
}
