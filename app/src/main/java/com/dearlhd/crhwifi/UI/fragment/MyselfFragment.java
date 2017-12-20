package com.dearlhd.crhwifi.UI.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class MyselfFragment extends Fragment {

    private View mRoot;

    private TextView mTvUsername;

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
    }
}
