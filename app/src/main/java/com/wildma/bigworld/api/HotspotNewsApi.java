package com.wildma.bigworld.api;


import com.wildma.bigworld.bean.HotspotNewsListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author       wildma
 * Date         2017/12/12
 * Desc	        ${热点新闻API}
 */
public interface HotspotNewsApi {

    //获取最新新闻
    @GET("/api/4/news/latest")
    Observable<HotspotNewsListBean> getLatestNews();

    //获取过往新闻
    @GET("/api/4/news/before/{date}")
    Observable<HotspotNewsListBean> getBeforeNews(@Path("date") String date);

}
