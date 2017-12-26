package com.wildma.bigworld.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Author       wildma
 * Date         2017/12/12
 * Desc	        ${}
 */
public class HotspotNewsListBean {
    @SerializedName("date")
    private String                         date;
    @SerializedName("top_stories")
    private ArrayList<HotspotNewsItemBean> mHotspotNewsItemBeen;
    @SerializedName("stories")
    private ArrayList<HotspotNewsItemBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<HotspotNewsItemBean> getHotspotNewsItemBeen() {
        return mHotspotNewsItemBeen;
    }

    public void setHotspotNewsItemBeen(ArrayList<HotspotNewsItemBean> hotspotNewsItemBeen) {
        this.mHotspotNewsItemBeen = hotspotNewsItemBeen;
    }

    public ArrayList<HotspotNewsItemBean> getStories() {
        return stories;
    }

    public void setStories(ArrayList<HotspotNewsItemBean> stories) {
        this.stories = stories;
    }
}
