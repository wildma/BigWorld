package com.wildma.bigworld.presenter.implPresenter;

import com.wildma.bigworld.api.ApiManage;
import com.wildma.bigworld.bean.HotspotNewsItemBean;
import com.wildma.bigworld.bean.HotspotNewsListBean;
import com.wildma.bigworld.presenter.IHotspotNewsPresenter;
import com.wildma.bigworld.presenter.implView.IHotspotNewsFragment;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author       wildma
 * Date         2017/12/12
 * Desc	        ${热点新闻P层实现类}
 */
public class HotspotNewsPresenterImpl extends BasePresenterImpl implements IHotspotNewsPresenter {

    private IHotspotNewsFragment mIHotspotNewsFragment;

    public HotspotNewsPresenterImpl(IHotspotNewsFragment IHotspotNewsFragment) {
        mIHotspotNewsFragment = IHotspotNewsFragment;
    }

    @Override
    public void getLatestNews() {
        mIHotspotNewsFragment.showProgressDialog();
        Subscription subscription = ApiManage.getInstence().getHotspotNewsApiService().getLatestNews()
                .map(new Func1<HotspotNewsListBean, HotspotNewsListBean>() {
                    @Override
                    public HotspotNewsListBean call(HotspotNewsListBean hotspotNewsListBean) {
                        String date = hotspotNewsListBean.getDate();
                        for (HotspotNewsItemBean hotspotNewsItemBean : hotspotNewsListBean.getStories()) {
                            hotspotNewsItemBean.setDate(date);
                        }
                        return hotspotNewsListBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotspotNewsListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIHotspotNewsFragment.hidProgressDialog();
                        mIHotspotNewsFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(HotspotNewsListBean hotspotNewsListBean) {
                        mIHotspotNewsFragment.hidProgressDialog();
                        mIHotspotNewsFragment.updateList(hotspotNewsListBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getBeforeNews(String date) {
        Subscription subscription = ApiManage.getInstence().getHotspotNewsApiService().getBeforeNews(date)
                .map(new Func1<HotspotNewsListBean, HotspotNewsListBean>() {
                    @Override
                    public HotspotNewsListBean call(HotspotNewsListBean hotspotNewsListBean) {
                        String date = hotspotNewsListBean.getDate();
                        for (HotspotNewsItemBean hotspotNewsItemBean : hotspotNewsListBean.getStories()) {
                            hotspotNewsItemBean.setDate(date);
                        }
                        return hotspotNewsListBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotspotNewsListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mIHotspotNewsFragment.hidProgressDialog();
                        mIHotspotNewsFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(HotspotNewsListBean hotspotNewsListBean) {
                        mIHotspotNewsFragment.hidProgressDialog();
                        mIHotspotNewsFragment.updateList(hotspotNewsListBean);
                    }
                });
        addSubscription(subscription);
    }

}
