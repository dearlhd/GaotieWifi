package com.dearlhd.crhwifi.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Weather;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/23.
 */
public class WeatherAdapter extends BaseAdapter {
    private Context mContext;
    private List<Weather> mWeatherList;

    public WeatherAdapter (Context context, List<Weather> weatherList) {
        mContext = context;
        mWeatherList = weatherList;
    }

    @Override
    public int getCount() {
        return mWeatherList.size();
    }

    @Override
    public Object getItem(int i) {
        return mWeatherList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_weather, null);
            holder = new ViewHolder();
            holder.tvDate = (TextView) view.findViewById(R.id.tv_weather_date);
            holder.ivIcon = (ImageView) view.findViewById(R.id.iv_weather_icon);
            holder.tvType = (TextView) view.findViewById(R.id.tv_weather_type);
            holder.tvTemperature = (TextView) view.findViewById(R.id.tv_weather_temperature);
            holder.tvWindDirection = (TextView) view.findViewById(R.id.tv_weather_wind_direction);
            holder.tvWindPower = (TextView) view.findViewById(R.id.tv_weather_wind_power);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Weather weather = mWeatherList.get(i);
        holder.tvDate.setText(weather.getDate());

        if (weather.getType().equals("晴")) {
            holder.ivIcon.setImageResource(R.drawable.ic_weather_sunny);
        } else if (weather.getType().equals("多云")) {
            holder.ivIcon.setImageResource(R.drawable.ic_weather_cloudy);
        } else if (weather.getType().contains("雪")) {
            holder.ivIcon.setImageResource(R.drawable.ic_weather_snowy);
        } else if (weather.getType().contains("雨")) {
            holder.ivIcon.setImageResource(R.drawable.ic_weather_rainy);
        } else {
            holder.ivIcon.setImageResource(R.drawable.ic_weather_cloudy);
        }

        holder.tvType.setText(weather.getType());

        String lowStr = weather.getTemperatureLow();
        String low = lowStr.substring(3, lowStr.length()-1);

        String highStr = weather.getTemperatureHigh();
        String high = highStr.substring(3, highStr.length()-1);

        holder.tvTemperature.setText(low + "~" + high + "℃");
        holder.tvWindDirection.setText(weather.getWindDirection());
        holder.tvWindPower.setText(weather.getWindPower());

        return view;
    }

    class ViewHolder {
        TextView tvDate;
        ImageView ivIcon;
        TextView tvType;
        TextView tvTemperature;
        TextView tvWindDirection;
        TextView tvWindPower;
    }
}
