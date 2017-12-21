package com.dearlhd.crhwifi.UI.fragment;

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
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class MyselfFragment extends Fragment {

    private View mRoot;

    private TextView mTvUsername;
    private LinearLayout mLlSignIn;

    private PopupWindow mSignInWindow;

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
    }
}
