package com.dianniu.common.params;

/**
 * Created by guodong on 2018/4/17.
 */

public interface NetWordParams {

    /** 授权口令name */
    String TOKEN = "token";
    /** 签名name */
    String SIGN = "sign";
    /** 时间戳name */
    String TS = "ts";
    /** userId */
    String USERID = "user_id";
    /** 设置超时 */
    int DEFAULT_TIMEOUT = 5;
    /** POST请求 */
    String POST = "post";
    /** GET请求 */
    String GET = "get";
    /** 日志拦截 */
    String INTERCEPTOR = "interceptor";
}
