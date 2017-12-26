package com.wildma.bigworld.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Author       wildma
 * Date         2017/12/11
 * Desc	        ${Activity基类}
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
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
