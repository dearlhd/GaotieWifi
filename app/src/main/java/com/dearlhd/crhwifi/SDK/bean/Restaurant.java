package com.dearlhd.crhwifi.SDK.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/25.
 */
public class Restaurant {
    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("adname")
    private String area;

    @SerializedName("photos")
    private List<Photo> photos;

    @SerializedName("biz_ext")
    private Info info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public class Photo {
        @SerializedName("url")
        public String url;
    }

    public class Info {
        @SerializedName("cost")
        public double cost;

        @SerializedName("rating")
        public double rating;
    }
}
