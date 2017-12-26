package com.wildma.bigworld.presenter.implView;


import com.wildma.bigworld.bean.HotspotNewsListBean;

/**
 * Author     wildma
 * DATE       2017/12/11
 * Desc	      ${}
 */
public interface IHotspotNewsFragment extends IBaseFragment {
    /**
     * 更新数据列表
     *
     * @param hotspotNewsListBean
     */
    void updateList(HotspotNewsListBean hotspotNewsListBean);
}
