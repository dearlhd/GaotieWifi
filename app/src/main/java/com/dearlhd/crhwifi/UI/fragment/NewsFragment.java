package com.dearlhd.crhwifi.UI.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.UI.adapter.NewsAdapter;
import com.dearlhd.crhwifi.UI.view.PullToRefreshListView;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class NewsFragment extends Fragment {

    private View mRoot;

    private PullToRefreshListView mNewsListView;
    private NewsAdapter mNewsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_news, container, false);
            initView();
        }

        ViewGroup parent = (ViewGroup) mRoot.getParent();
        if (parent != null) {
            parent.removeView(mRoot);
        }

        return mRoot;
    }

    private void initView () {
        mNewsListView = (PullToRefreshListView) mRoot.findViewById(R.id.lv_news);

    }
}
