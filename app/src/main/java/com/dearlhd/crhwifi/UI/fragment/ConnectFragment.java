package com.dearlhd.crhwifi.UI.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Journey;
import com.dearlhd.crhwifi.SDK.util.CityParser;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;

import java.util.List;

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

    private Journey mJourney;

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
                            mTvCurrentStation.setText("上海");
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

    private WifiInfo getWifiInfo () {
        WifiManager wifi_service = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifi_service.getConnectionInfo();
        return wifiInfo;
    }
}
