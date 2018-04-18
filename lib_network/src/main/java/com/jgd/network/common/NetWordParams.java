package com.jgd.network.common;

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

    /** 返回码 */
    String CODE = "code";
    String MSG = "msg";

    //状态码
    int UNAUTHORIZED_CODE = 401;
    int FORBIDDEN_CODE = 403;
    int NOT_FOUND_CODE = 404;
    int REQUEST_TIMEOUT_CODE = 408;
    int INTERNAL_SERVER_ERROR_CODE = 500;
    int BAD_GATEWAY_CODE = 502;
    int SERVICE_UNAVAILABLE_CODE = 503;
    int GATEWAY_TIMEOUT_CODE = 504;

    //状态
    String UNKNOWN_ERROR = "未知错误";
    String PARSING_ERROR = "未知错误";
    String FORBIDDEN = "未添加网络访问权限";
    String ERROR = "网络错误";
}
