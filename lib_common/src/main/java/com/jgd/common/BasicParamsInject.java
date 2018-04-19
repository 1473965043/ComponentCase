package com.jgd.common;

import com.jgd.common.util.AppUtil;
import com.jgd.network.common.BasicParamsInterceptor;
import com.jgd.network.common.IBasicDynamic;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.Interceptor;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/5 17:59
 * <p/>
 * Description: 拦截器 - 用于添加签名参数
 */
class BasicParamsInject {
    private BasicParamsInterceptor interceptor;

    BasicParamsInject() {
        // 设置静态参数
        interceptor = new BasicParamsInterceptor.Builder()
                .addBodyParam("", "")
                .addBodyParam("", "")
                .build();
        // 设置动态参数
        interceptor.setIBasicDynamic(new IBasicDynamic() {
            @Override
            public String signParams(String postBodyString) {
                //post提交动态添加参数
                return AppUtil.dynamicParams(postBodyString);
            }

            @Override
            public Map signParams(Map map) {
                //get提交动态添加参数
                TreeMap temp = new TreeMap(map);
                return AppUtil.dynamicParams(temp);
            }

            @Override
            public Map signHeadParams(Map headMap) {
                return AppUtil.signParams(headMap);
            }
        });
    }

    Interceptor getInterceptor() {
        return interceptor;
    }
}
