package com.jgd.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/4/17.
 */

public interface RetrofitApiService {
    /**
     * get请求获取服务器数据
     * @param url 带参数，且拼接好的网址
     * @return
     */
    @GET
    Observable<String> onGetData(@Url String url);

}
