package com.dianniu.common.util;

import android.util.Log;

/**
 * Created by guodong on 2018/4/17.
 * 日志工具类
 */

public class LogUtil {

    public static void e(String tag, String msg){
        if(AppUtil.isDebug()){
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg){
        if(AppUtil.isDebug()){
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg){
        if(AppUtil.isDebug()){
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg){
        if(AppUtil.isDebug()){
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg){
        if(AppUtil.isDebug()){
            Log.v(tag, msg);
        }
    }
}
