package com.dearlhd.crhwifi.SDK.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dearlhd on 2017/12/22.
 */
public class Weather {
    @SerializedName("aqi")
    private int aqi;

    @SerializedName("date")
    private String date;

    @SerializedName("fl")
    private String windPower;

    @SerializedName("fx")
    private String windDirection;

    @SerializedName("high")
    private String temperatureHigh;

    @SerializedName("low")
    private String temperatureLow;

    @SerializedName("type")
    private String type;

    @SerializedName("sunrise")
    private String sunrise;

    @SerializedName("sunset")
    private String sunset;

    @SerializedName("notice")
    private String notice;

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(String temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public String getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(String temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
