package com.wildma.bigworld.global;

import android.app.Application;
import android.content.Context;

/**
 * Author       wildma
 * Date         2017/12/11
 * Desc	        ${}
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    /**
     * 获取Context
     *
     * @return
     */
    public static Context getContext() {
        return myApplication;
    }
}
