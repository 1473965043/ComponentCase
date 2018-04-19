package com.jgd.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by guodong on 2018/4/12.
 * 与控件有关的工具类
 */

public class ViewUtil {

    /**
     * 通过view暴力获取getContext()(Android不支持view.getContext()了)
     * @param view 要获取context的view
     * @return 返回一个activity
     * 参考链接 -- https://segmentfault.com/a/1190000014242977
     */
    public static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (Activity) view.getRootView().getContext();
    }

    /**
     * 通过view暴力获取getContext()(Android不支持view.getContext()了)
     * @param view 要获取context的view
     * @return 返回一个activity
     * 通过反射获取activity
     */
    public static Activity getViewActivity(View view) {
        Activity activity = null;
        if (view.getContext().getClass().getName().contains("com.android.internal.policy.DecorContext")) {
            try {
                Field field = view.getContext().getClass().getDeclaredField("mPhoneWindow");
                field.setAccessible(true);
                Object obj = field.get(view.getContext());
                java.lang.reflect.Method m1 = obj.getClass().getMethod("getContext");
                activity = (Activity) (m1.invoke(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            activity = (Activity) view.getContext();
        }
        return activity;
    }
}
