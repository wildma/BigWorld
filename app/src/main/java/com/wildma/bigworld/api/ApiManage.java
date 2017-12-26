package com.wildma.bigworld.api;


import com.wildma.bigworld.global.MyApplication;
import com.wildma.bigworld.global.Constant;
import com.wildma.bigworld.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author       wildma
 * Date         2017/12/12
 * Desc	        ${Api管理类}
 */
public class ApiManage {

    public HotspotNewsApi mHotspotNewsApi;
    private Object hotspotNewsMonitor = new Object();
    public static ApiManage apiManage;
    private static File  httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "HotspotNewsCache");
    private static int   cacheSize          = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache              = new Cache(httpCacheDirectory, cacheSize);

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .build();


    public static ApiManage getInstence() {
        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }

    public HotspotNewsApi getHotspotNewsApiService() {
        if (mHotspotNewsApi == null) {
            synchronized (hotspotNewsMonitor) {
                if (mHotspotNewsApi == null) {
                    mHotspotNewsApi = new Retrofit.Builder()
                            .baseUrl(Constant.HOTSPOT_NEWS_BASEURL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(HotspotNewsApi.class);
                }
            }
        }
        return mHotspotNewsApi;
    }

}
