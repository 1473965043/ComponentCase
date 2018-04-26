package com.jgd.network.common;

import android.content.Context;

import okhttp3.Cache;

/**
 * Created by guodong on 2018/4/25.
 * 缓存内容提供器
 */

public class CacheProvide {

    private Context mContext;

    CacheProvide(Context context){
        this.mContext = context;
    }

    public Cache provideCache() {//使用应用缓存文件路径，缓存大小为10MB
        return new Cache(mContext.getCacheDir(), 10 * 1024 * 1024);
    }

    public static CacheProvide create(Context context){
        return new CacheProvide(context);
    }
}
