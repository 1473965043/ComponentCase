package com.jgd.network.common;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guodong on 2018/4/25.
 * 缓存拦截器
 * 参考：https://blog.csdn.net/u010286855/article/details/52608485
 * 参考：https://blog.csdn.net/gengqiquan/article/details/52200638
 * 参考：https://blog.csdn.net/qq_33463102/article/details/60869879
 * 参考：https://www.jianshu.com/p/7e692fe1709d
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String cache = request.header("Cache-Time");
        if (!TextUtils.isEmpty(cache)) {//缓存时间不为空
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for cache seconds
                    .header("Cache-Control", "max-age="+cache)
                    .build();
        }
        response.cacheResponse();
        return response;
    }

}
