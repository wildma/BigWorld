package com.wildma.bigworld.presenter.implView;

/**
 * Author     wildma
 * DATE       2017/12/11
 * Desc	      ${}
 */
public interface IBaseFragment {
    /**
     * 显示加载圈
     */
    void showProgressDialog();

    /**
     * 隐藏加载圈
     */
    void hidProgressDialog();

    /**
     * 显示错误信息
     *
     * @param error
     */
    void showError(String error);
}
