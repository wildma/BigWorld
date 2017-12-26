package com.wildma.bigworld.presenter;

/**
 * Author       wildma
 * Date         2017/12/12
 * Desc	        ${热点新闻P层接口}
 */
public interface IHotspotNewsPresenter extends BasePresenter {
    /**
     * 获取最新新闻
     */
    void getLatestNews();

    /**
     * 获取过往新闻
     *
     * @param date
     */
    void getBeforeNews(String date);

}
