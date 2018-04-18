package com.jgd.network.common;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guodong on 2018/4/18.
 */

public class ApiException extends RuntimeException{

    private int code;
    private String msg;

    public ApiException(){}

    public ApiException(JSONObject jsonObject) {
        try {
            code = jsonObject.getInt(NetWordParams.CODE);
            msg = jsonObject.getString(NetWordParams.MSG);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
