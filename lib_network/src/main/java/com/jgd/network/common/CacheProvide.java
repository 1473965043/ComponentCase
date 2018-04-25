package com.jgd.network.common;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;

/**
 * Created by guodong on 2018/4/25.
 * 缓存内容提供器
 */

public class CacheProvide {

    CacheProvide(){}

    public Cache provideCache() {//使用应用缓存文件路径，缓存大小为10MB
        File dir = new File(Environment.getExternalStorageDirectory(), "AppCache");
        if(!dir.exists()){
            dir.mkdir();
        }
        return new Cache(dir, 10 * 1024 * 1024);
    }

    public static CacheProvide create(){
        return new CacheProvide();
    }
}
