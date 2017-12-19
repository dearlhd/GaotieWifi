package com.dearlhd.crhwifi.UI.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.News;
import com.dearlhd.crhwifi.SDK.network.CRHWifiApi;
import com.dearlhd.crhwifi.SDK.response.NewsResponse;
import com.dearlhd.crhwifi.UI.adapter.NewsAdapter;
import com.dearlhd.crhwifi.UI.view.PullToRefreshListView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by dearlhd on 2017/12/13.
 */
public class NewsFragment extends Fragment {

    private View mRoot;

    private Subscriber<NewsResponse> mSubscriber;

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

        mSubscriber = new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(NewsResponse newsResponse) {
                List<News> newsList = newsResponse.newses;
                NewsAdapter adapter = new NewsAdapter(getContext(), newsList);
                mNewsListView.setAdapter(adapter);
            }
        };

        CRHWifiApi.getInstance().getNewsList(mSubscriber);
    }
}
