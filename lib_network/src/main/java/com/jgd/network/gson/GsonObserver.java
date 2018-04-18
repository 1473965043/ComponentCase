package com.jgd.network.gson;

import com.jgd.network.callback.ResponseCallBack;
import com.jgd.network.common.HttpResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by guodong on 2018/4/17.
 */

public class GsonObserver<T> implements Observer<HttpResult<T>> {

    private ResponseCallBack<T> mResultListener;

    public GsonObserver responseCallBack(ResponseCallBack resultListener){
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
    public void onNext(HttpResult<T> httpResult) {
        mResultListener.onSuccess(httpResult.getResult(), httpResult.getError_code(), httpResult.getReason());
    }

    @Override
    public void onError(Throwable e) {
        if(mResultListener != null){
            mResultListener.onError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        if(mResultListener != null){
            mResultListener.onCompleted();
        }
    }
}
