package com.wildma.bigworld.dagger2.module;

import com.wildma.bigworld.fragment.HotspotNewsFragment;
import com.wildma.bigworld.presenter.implPresenter.HotspotNewsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Author       wildma
 * Date         2018/1/8
 * Desc	        ${TODO}
 */

@Module //实例化目标类需要依赖的对象
public class HotspotNewsFragmentModule {

    HotspotNewsFragment mFragment;

    public HotspotNewsFragmentModule(HotspotNewsFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 该方法是用来提供实例化对象给目标类的
     *
     * @return
     */
    @Provides
    HotspotNewsPresenterImpl provideHotspotNewsPresenterImpl() {
        return new HotspotNewsPresenterImpl(mFragment);
    }
}
