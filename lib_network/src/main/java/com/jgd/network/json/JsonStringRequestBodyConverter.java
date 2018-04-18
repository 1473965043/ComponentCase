package com.jgd.network.json;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by guodong on 2018/4/18.
 */

public class JsonStringRequestBodyConverter<T> implements Converter<T, RequestBody> {

    @Override
    public RequestBody convert(T value) throws IOException {
        String string = new Gson().toJson(value);
        return RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), string);
    }
}
