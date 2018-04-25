package com.jgd.network.common;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by guodong on 2018/4/25.
 * 给请求添加参数
 */

public class RequestParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        /** 第一种 (GET请求)添加在请求链接尾部 例如：www.baidu.com/s?key1=value1&key2=value2 */
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if(request.method().equals("GET")){
            HttpUrl httpUrl = request.url().newBuilder()
                    .addQueryParameter("key", "value")
                    .build();
            //添加到请求里
            builder.url(httpUrl);
        }

        /** 第二种 (POST请求)表单提交 */
        if(request.method().equals("POST")){
            RequestBody formBody = new FormBody.Builder()
                    .add("key", "value")
                    .build();
            String postBodyString = bodyToString(request.body());
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
            //添加到请求里, string转回成RequestBody
            builder.post(RequestBody.create(formBody.contentType(), postBodyString));
        }

        /** 第三种 header上添加 */
        Headers headers = request.headers().newBuilder()
                .add("key", "value")
                .build();
        builder.headers(headers);
        return chain.proceed(builder.build());
    }

    // RequestBody to String
    private String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null)
                request.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (IOException e) {
            return "did not work";
        }
    }
}
