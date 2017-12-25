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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;
import com.dearlhd.crhwifi.UI.activity.HistoryActivity;
import com.dearlhd.crhwifi.UI.activity.LuckyDrawActivity;
import com.dearlhd.crhwifi.UI.activity.PublicEnergyActivity;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class MyselfFragment extends Fragment {

    private View mRoot;

    private TextView mTvUsername;
    private LinearLayout mLlSignIn;

    private RelativeLayout mRlTask;
    private RelativeLayout mRlLuckyPan;
    private RelativeLayout mRlHistories;
    private RelativeLayout mRlPublicEnergy;

    private PopupWindow mSignInWindow;
    private PopupWindow mTaskWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_myself, container, false);
            initView();
        }

        ViewGroup parent = (ViewGroup) mRoot.getParent();
        if (parent != null) {
            parent.removeView(mRoot);
        }

        return mRoot;
    }

    private void initView () {
        mTvUsername = (TextView) mRoot.findViewById(R.id.tv_username);
        SQLiteHelper helper = new SQLiteHelper();
        mTvUsername.setText("用户" + helper.getUsername());

        mLlSignIn = (LinearLayout) mRoot.findViewById(R.id.ll_sign_in);
        initSignInWindow();
        mLlSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 0.5f;
                getActivity().getWindow().setAttributes(lp);
                mSignInWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        });

        mRlTask = (RelativeLayout) mRoot.findViewById(R.id.rl_task);
        initTaskWindow();
        mRlTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 0.5f;
                getActivity().getWindow().setAttributes(lp);
                mTaskWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        });

        mRlLuckyPan = (RelativeLayout) mRoot.findViewById(R.id.rl_lucky_pan);
        mRlLuckyPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LuckyDrawActivity.class);
                startActivity(intent);
            }
        });

        mRlHistories = (RelativeLayout) mRoot.findViewById(R.id.rl_histories);
        mRlHistories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        mRlPublicEnergy = (RelativeLayout) mRoot.findViewById(R.id.rl_public_energy);
        mRlPublicEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PublicEnergyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSignInWindow () {
        View signInView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_sign_in, null);
        mSignInWindow = new PopupWindow(getContext());
        mSignInWindow.setContentView(signInView);
        mSignInWindow.setWidth(800);
        mSignInWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mSignInWindow.setOutsideTouchable(true);
        mSignInWindow.setFocusable(true);
        mSignInWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_round_corner));
        mSignInWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        ImageView ivClose = (ImageView) signInView.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignInWindow.dismiss();
            }
        });

        TextView tvLuckyPan = (TextView)signInView.findViewById(R.id.tv_to_lucky_pan);
        tvLuckyPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignInWindow.dismiss();
                Intent intent = new Intent(getActivity(), LuckyDrawActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTaskWindow () {
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
