package com.dearlhd.crhwifi.SDK.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dearlhd on 2017/12/15.
 */
public class News {
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("img_path")
    private String image;

    @SerializedName("source")
    private String source;

    @SerializedName("publish_time")
    private String time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
