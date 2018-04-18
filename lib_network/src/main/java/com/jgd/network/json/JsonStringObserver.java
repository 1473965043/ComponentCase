package com.jgd.network.json;

import android.net.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.jgd.network.common.ApiException;
import com.jgd.network.common.HttpResult;
import com.jgd.network.callback.ResponseCallBack;
import com.jgd.network.common.NetWordParams;
import com.jgd.network.util.ReflectUtil;

import org.json.JSONException;

import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by guodong on 2018/4/17.
 */

public class JsonStringObserver implements Observer<String> {

    private Type type;

    private ResponseCallBack mResultListener;

    public JsonStringObserver(Class cls){
        type = ReflectUtil.type(HttpResult.class, cls);
    }

    public JsonStringObserver(Class fCls, Class cls){
        type = ReflectUtil.type(HttpResult.class, ReflectUtil.type(fCls, cls));
    }

    public JsonStringObserver responseCallBack(ResponseCallBack resultListener){
        this.mResultListener = resultListener;
        return this;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(mResultListener != null){
            mResultListener.onStart();
        }
    }

    @Override
    public void onNext(String string) {
        HttpResult httpResult = new Gson().fromJson(string, type);
        mResultListener.onSuccess(httpResult.getResult(), httpResult.getError_code(), httpResult.getReason());
    }

    @Override
    public void onError(Throwable e) {
        if (mResultListener != null) {
            return;
        }
        if (e instanceof HttpException) {                                  //HTTP错误
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case NetWordParams.UNAUTHORIZED_CODE:
                case NetWordParams.FORBIDDEN_CODE:                         //权限错误，需要实现
                    mResultListener.onError(NetWordParams.FORBIDDEN);
                    break;
                case NetWordParams.NOT_FOUND_CODE:
                case NetWordParams.REQUEST_TIMEOUT_CODE:
                case NetWordParams.GATEWAY_TIMEOUT_CODE:
                case NetWordParams.INTERNAL_SERVER_ERROR_CODE:
                case NetWordParams.BAD_GATEWAY_CODE:
                case NetWordParams.SERVICE_UNAVAILABLE_CODE:
                default:
                    mResultListener.onError(NetWordParams.ERROR);          //均视为网络错误
                    break;
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            mResultListener.onError(NetWordParams.PARSING_ERROR);          //均视为解析错误
        } else if(e instanceof ApiException){
            mResultListener.onError(NetWordParams.PARSING_ERROR);          //返回码错误
        }else {
            mResultListener.onError(NetWordParams.UNKNOWN_ERROR);          //未知错误
        }

    }

    @Override
    public void onComplete() {
        if(mResultListener != null){
            mResultListener.onCompleted();
        }
    }
}
