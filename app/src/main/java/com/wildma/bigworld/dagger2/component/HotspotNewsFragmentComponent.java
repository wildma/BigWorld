package com.wildma.bigworld.dagger2.component;

import com.wildma.bigworld.dagger2.module.HotspotNewsFragmentModule;
import com.wildma.bigworld.fragment.HotspotNewsFragment;

import dagger.Component;

/**
 * Author       wildma
 * Date         2018/1/8
 * Desc	        ${TODO}
 */
@Component(modules = HotspotNewsFragmentModule.class) //作为Module与目标类之间的桥梁
public interface HotspotNewsFragmentComponent {

    /**
     * 定义注入的方法
     *
     * @param fragment
     */
    void inject(HotspotNewsFragment fragment);
}
