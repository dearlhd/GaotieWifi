package com.dearlhd.crhwifi.UI.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dearlhd on 2017/12/15.
 */
public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<News> mNewsList;

    public NewsAdapter (Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    @Override
    public int getCount() {
        if (mNewsList == null) {
            mNewsList = new ArrayList<>();
        }
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
            holder.tvSource = (TextView) convertView.findViewById(R.id.tv_news_info);
            holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_news_image);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        News news = mNewsList.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvSource.setText(news.getSource());
        byte[] bitmapArray = decodeBase64(news.getImage());
        holder.ivImage.setImageBitmap(BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length));

        return convertView;
    }

    private byte[] decodeBase64 (String base) {
        return Base64.decode(base.getBytes(), Base64.DEFAULT);
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvSource;
        ImageView ivImage;
    }
}
