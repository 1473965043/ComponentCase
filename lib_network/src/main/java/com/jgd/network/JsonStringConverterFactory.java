package com.jgd.network;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by guodong on 2018/4/17.
 * 自定义Factory返回JsonString
 */

public class JsonStringConverterFactory extends Converter.Factory {

    public static JsonStringConverterFactory create(){
        return new JsonStringConverterFactory();
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new CustomResponseBodyConverter();
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new CustomRequestBodyConverter();
    }

    public class CustomResponseBodyConverter implements Converter<ResponseBody, Object>{

        @Override
        public Object convert(ResponseBody value) throws IOException {
            //获取value的值
            String response = value.string();
            //返回json字符串
//            LogUtil.d(LogUtil.NETWORK, response);
            return response;
        }
    }

    public class CustomRequestBodyConverter<T> implements Converter<T, RequestBody>{

        @Override
        public RequestBody convert(T value) throws IOException {
            String string = new Gson().toJson(value);
//            LogUtil.i(LogUtil.NETWORK, string);
            return RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), string);
        }
    }
}
