package com.dearlhd.crhwifi.UI.fragment;

import android.content.Intent;
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
import com.dearlhd.crhwifi.UI.activity.DiscoverDetailActivity;
import com.dearlhd.crhwifi.UI.activity.RecommendActivity;
import com.dearlhd.crhwifi.UI.activity.WeatherActivity;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class DiscoveryFragment extends Fragment {

    private View mRoot;

    private LinearLayout mLlWeather;
    private LinearLayout mLlFood;
    private LinearLayout mLlHotel;
    private LinearLayout mLlTask;

    private LinearLayout mLlDiscover1;
    private LinearLayout mLlDiscover2;
    private LinearLayout mLlDiscover3;
    private LinearLayout mLlDiscover4;

    private PopupWindow mTaskWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_discovery, container, false);
            initView();
        }

        ViewGroup parent = (ViewGroup) mRoot.getParent();
        if (parent != null) {
            parent.removeView(mRoot);
        }

        return mRoot;
    }

    private void initView() {
        mLlWeather = (LinearLayout) mRoot.findViewById(R.id.ll_weather);
        mLlFood = (LinearLayout) mRoot.findViewById(R.id.ll_food_recommend);
        mLlHotel = (LinearLayout) mRoot.findViewById(R.id.ll_hotel_recommend);
        mLlTask = (LinearLayout) mRoot.findViewById(R.id.ll_task);

        mLlWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                startActivity(intent);
            }
        });

        mLlFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RecommendActivity.class);
                intent.putExtra("state", "FOOD");
                startActivity(intent);
            }
        });

        mLlHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RecommendActivity.class);
                intent.putExtra("state", "HOTEL");
                startActivity(intent);
            }
        });

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

        mLlDiscover1 = (LinearLayout) mRoot.findViewById(R.id.ll_discover1);
        mLlDiscover2 = (LinearLayout) mRoot.findViewById(R.id.ll_discover2);
        mLlDiscover3 = (LinearLayout) mRoot.findViewById(R.id.ll_discover3);
        mLlDiscover4 = (LinearLayout) mRoot.findViewById(R.id.ll_discover4);

        mLlDiscover1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiscoverDetailActivity.class);
                String url = "http://mp.weixin.qq.com/s/T4fLh0jjNATFPLu6-8hjYg";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        mLlDiscover2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiscoverDetailActivity.class);
                String url = "http://mp.weixin.qq.com/s/2NtqBkB4FnjgFSecCKNeBA";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        mLlDiscover3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiscoverDetailActivity.class);
                String url = "http://mp.weixin.qq.com/s/A6yqDqx1TXs1a9AG0HINpA";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        mLlDiscover4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiscoverDetailActivity.class);
                String url = "http://mp.weixin.qq.com/s/_dSU0BMd6wFcYfOmbVrVvA";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
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
