package com.dianniu.common.util;

import android.text.TextUtils;

import com.dianniu.common.BuildConfig;
import com.jgd.network.common.NetWordParams;
import com.jgd.network.util.MDUtil;

import java.io.File;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by guodong on 2018/4/14.
 */

public class AppUtil {

    public static boolean isDebug(){
        return BuildConfig.DEBUG;
    }

    /**
     * 对字符串进行签名
     */
    public static String dynamicParams(String postBodyString) {
        TreeMap<String, String> treeMap = splitPostString(postBodyString);
        treeMap = dynamicParams(treeMap);
        //String sign = getSign(treeMap);
        //treeMap.put(Constant.SIGNA, sign);
        return getPostParamsStr(treeMap);
    }

    /**
     * 动态拼接请求参数
     */
    public static TreeMap<String, String> dynamicParams(TreeMap<String, String> map) {
        String ts = String.valueOf(System.currentTimeMillis() / 1000);
        //map.put(Constant.TS, ts);
        String token  = "";
        String userId = "";
        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(userId)) {
            map.put(NetWordParams.TOKEN, token);
            map.put("userid", userId);
        }
        return map;
    }

    /**
     * 提交文件 对Map数据进行签名
     */
    public static String signParams(TreeMap<String, String> treeMap) {
        TreeMap commonParamsTreeMap = new TreeMap<>();
        //commonParamsTreeMap.put(Constant.APP_KEY, BaseParams.APP_KEY);
        commonParamsTreeMap.put("", "");
        treeMap.putAll(commonParamsTreeMap);
        String sign = getSign(treeMap);
        return sign;
    }

    /**
     * 对Map数据进行签名
     */
    public static Map signParams(Map<String, String> treeMap) {
      /*  TreeMap map = new TreeMap();
        map.putAll(commonParamsTreeMap);
        map.putAll(treeMap);
        map = dynamicParams(map);
        String  sign    = getSign(map);*/
        TreeMap headMap = new TreeMap();
        headMap.put("", "2");
        return headMap;
    }

    /**
     * 将map拼装成请求字符串
     *
     * @return 返回请求参数
     */
    public static String getPostParamsStr(TreeMap map) {
        Iterator it = map.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        try {
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                sb.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sb.toString().length() > 1) {
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }

    /**
     * 分割请求参数，放入treeMap中,拼接动态参数
     *
     * @param postBodyString
     *         请求参数
     */
    private static TreeMap<String, String> splitPostString(String postBodyString) {
        TreeMap<String, String> map = new TreeMap<>();
        for (String s : postBodyString.split("&")) {
            String[] keyValue = s.split("=");
            map.put(keyValue[0], keyValue.length > 1 ? keyValue[1] : "");
        }
        return map;
    }

    /**
     * 一般接口调用-signa签名生成规则
     *
     * @param map
     *         有序请求参数map
     */
    private static String getSign(TreeMap map) {
        String signa = "";
        try {
            Iterator  it = map.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getValue() instanceof File)
                    continue;//URLEncoder.encode(, "UTF-8")
                sb.append(entry.getKey()).append("=").append(URLDecoder.decode(entry.getValue().toString(), "UTF-8")).append("|");
            }
            // 所有请求参数排序后的字符串后进行MD5（32）
            //signa = MDUtil.encode(MDUtil.TYPE.MD5, sb.toString());
            // 得到的MD5串拼接appsecret再次MD5，所得结果转大写
            String sign = "";
            if (sb.toString().length() > 1) {
                sign = sb.toString().substring(0, sb.length() - 1);
            } else {
                sign = sb.toString();
            }
            System.out.println("sb.toString()" + "" + sign);
            signa = MDUtil.encode(MDUtil.TYPE.MD5, "" + sign).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signa;
    }
}
