package com.wildma.bigworld.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author       wildma
 * Date         2017/12/11
 * Desc	        ${Fragment基类}
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder binder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = ButterKnife.bind(this, view);
        initView();
        initListener();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null)
            binder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 初始化监听事件
     */
    protected void initListener() {

    }

    /**
     * 初始化view
     */
    protected void initView() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 获取当前layout的ID
     *
     * @return
     */
    protected abstract int getLayoutId();
}
