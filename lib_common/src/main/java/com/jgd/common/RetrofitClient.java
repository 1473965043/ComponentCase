package com.jgd.common;

import com.jgd.network.common.CacheInterceptor;
import com.jgd.network.common.CacheProvide;
import com.jgd.network.common.NetWordParams;
import com.jgd.network.common.RequestParamsInterceptor;
import com.jgd.network.json.JsonStringConverterFactory;
import com.jgd.network.util.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guodong on 2017/7/19.
 */

public class RetrofitClient {

    /** 唯一的实例对象 */
    private static RetrofitClient mInstance;
    /** 日志拦截器 */
    private static HttpLoggingInterceptor loggingInterceptor;

    private OkHttpClient okHttpClient;

    private Retrofit retrofit;

    /** 单例模式 */
    public static RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    private RetrofitClient(){
        loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.e(NetWordParams.INTERCEPTOR, message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new RequestParamsInterceptor())//参数拦截器
                .addInterceptor(loggingInterceptor)//日志拦截器
                .addNetworkInterceptor(new CacheInterceptor())
                .cache(CacheProvide.create().provideCache())
                .connectTimeout(NetWordParams.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NetWordParams.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NetWordParams.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//Gson解析网络数据
                .addConverterFactory(JsonStringConverterFactory.create())//自定义返回数据
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UrlUtils.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    /**
     * 执行网络任务
     * @param observer 观察者
     */
    public void execute(Observable<?> observable, Observer observer){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public <T> T getService(Class<T> cls){
        return retrofit.create(cls);
    }
}
