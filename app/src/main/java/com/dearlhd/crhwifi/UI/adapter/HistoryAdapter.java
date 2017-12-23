package com.dearlhd.crhwifi.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.History;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/23.
 */
public class HistoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<History> mHistories;

    public HistoryAdapter (Context context, List<History> histories) {
        mContext = context;
        mHistories = histories;
    }

    @Override
    public int getCount() {
        return mHistories.size();
    }

    @Override
    public Object getItem(int i) {
        return mHistories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_history, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) view.findViewById(R.id.tv_history_title);
            holder.tvTime = (TextView) view.findViewById(R.id.tv_history_time);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        History history = mHistories.get(i);
        holder.tvTime.setText(history.getTitle());
        holder.tvTime.setText(history.getBrowseTime());

        return view;
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvTime;
    }
}
