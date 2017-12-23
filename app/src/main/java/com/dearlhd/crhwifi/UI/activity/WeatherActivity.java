package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.City;
import com.dearlhd.crhwifi.SDK.bean.Weather;
import com.dearlhd.crhwifi.SDK.network.CRHWifiApi;
import com.dearlhd.crhwifi.SDK.response.WeatherResponse;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;
import com.dearlhd.crhwifi.UI.adapter.WeatherAdapter;

import java.util.List;

import rx.Subscriber;

/**
 * Created by dearlhd on 2017/12/23.
 */
public class WeatherActivity extends Activity {

    private TextView mTvTodayTemperature;
    private TextView mTvTodayType;
    private ListView mLvWeatherList;

    private Subscriber<WeatherResponse> mWeatherSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView ();
        getWeatherData();
    }

    private void initView() {
        mTvTodayTemperature = (TextView) findViewById(R.id.tv_today_temperature);
        mTvTodayType = (TextView) findViewById(R.id.tv_today_type);
        mLvWeatherList = (ListView) findViewById(R.id.lv_weather_one_week);
    }

    private void getWeatherData () {
        mWeatherSubscriber = new Subscriber<WeatherResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(WeatherResponse weatherResponse) {
                List<Weather> weatherList = weatherResponse.forecast.weatherList;

                Weather todayWeather = weatherList.get(0);
                String lowStr = todayWeather.getTemperatureLow();
                String low = lowStr.substring(3, lowStr.length()-1);

                String highStr = todayWeather.getTemperatureHigh();
                String high = highStr.substring(3, highStr.length()-1);

                mTvTodayTemperature.setText(low + "~" + high);
                mTvTodayType.setText(todayWeather.getType());

                WeatherAdapter adapter = new WeatherAdapter(WeatherActivity.this, weatherList);
                mLvWeatherList.setAdapter(adapter);
            }
        };

        SQLiteHelper helper = new SQLiteHelper();
        CRHWifiApi.getInstance().getWeather(mWeatherSubscriber, new City(helper.getJourney().getDestination()));
    }
}
