package com.jgd.network.json;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

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
        return new JsonStringResponseBodyConverter();
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JsonStringRequestBodyConverter();
    }
}
