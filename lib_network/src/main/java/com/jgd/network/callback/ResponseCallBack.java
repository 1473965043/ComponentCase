package com.jgd.network.callback;

/**
 * Created by guodong on 2017/12/8.
 */

public abstract class ResponseCallBack<T> implements CallBack<T> {

    /** 结束操作 */
    public void onCompleted(){}

    /** 开始操作 */
    public void onStart() {}
}