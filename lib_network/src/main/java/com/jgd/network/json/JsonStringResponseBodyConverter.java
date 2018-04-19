package com.jgd.network.json;

import com.google.gson.Gson;
import com.jgd.network.common.ApiException;
import com.jgd.network.common.NetWordParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by guodong on 2018/4/18.
 */

public class JsonStringResponseBodyConverter implements Converter<ResponseBody, String>{
    @Override
    public String convert(ResponseBody value) throws IOException {
        //获取value的值
        try {
            String response = value.string();
            JSONObject jsonObject = new Gson().fromJson(response, JSONObject.class);
            if(jsonObject.getInt(NetWordParams.CODE) != 0
                    && jsonObject.getInt(NetWordParams.CODE) != 1){
                throw new ApiException();
            }
            return response;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
