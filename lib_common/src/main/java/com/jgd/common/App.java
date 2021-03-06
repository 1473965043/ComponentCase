package com.jgd.common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jgd.common.util.AppUtil;

/**
 * Created by guodong on 2018/4/14.
 */

public class App extends Application {

    public static App app;

    public static App getInstance() {
        if(app == null){
            app = new App();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    /** 初始化ARouter */
    private void initARouter() {
        if (AppUtil.isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
