package com.dearlhd.crhwifi.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.UI.activity.RecommendActivity;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class DiscoveryFragment extends Fragment {

    private View mRoot;

    private LinearLayout mLlFood;
    private LinearLayout mLlHotel;

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

    private void initView () {
        mLlFood = (LinearLayout) mRoot.findViewById(R.id.ll_food_recommend);
        mLlHotel = (LinearLayout) mRoot.findViewById(R.id.ll_hotel_recommend);

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
    }
}
