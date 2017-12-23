package com.dearlhd.crhwifi.SDK.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dearlhd on 2017/12/19.
 */
public class History {
    @SerializedName("user_id")
    private long userId;

    @SerializedName("news_id")
    private long newsId;

    @SerializedName("title")
    private String title;

    @SerializedName("browse_time")
    private String browseTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(String browseTime) {
        this.browseTime = browseTime;
    }
}
