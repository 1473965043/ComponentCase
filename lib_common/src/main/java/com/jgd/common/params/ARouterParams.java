package com.jgd.common.params;

/**
 * Created by guodong on 2018/4/12.
 * 接口类 -- 组件名集合
 */

public interface ARouterParams {

    /** 公共参数 */
    String AROUTER_BUNDLE = "ARouter_Bundle";

    /** App模块 */
    String APP_MAIN = "/app/main";//主页
    String APP_LOGIN = "/app/login";//登录

    /** HomePage模块 */
    String HOMEPAGE_MAIN = "/homepage/function";

    /** mine模块 */
    String MINE_MAIN = "/mine/center";

    /** other模块 */
}
