package com.dianniu.common.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dianniu.common.bean.MessageEvent;
import com.dianniu.common.params.ARouterParams;
import com.jgd.network.RetrofitApiService;
import com.jgd.network.RetrofitManage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observer;

/**
 * Created by guodong on 2018/4/12.
 */

public class BaseActivity extends AppCompatActivity {

    public RetrofitManage retrofitManage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitManage = RetrofitManage.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        /* Do something */
    }

    /** 粘性事件 */
    @Subscribe(sticky = true)
    public void onStickyEvent(MessageEvent event) {
        /* Do something */
    }

    /**
     * 执行ARouter
     * @param path
     */
    public void executeARouter(String path){
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * 执行ARouter
     * @param path
     * @param bundle 参数
     */
    public void executeARouter(String path, Bundle bundle){
        ARouter.getInstance()
                .build(path)
                .withBundle(ARouterParams.AROUTER_BUNDLE, bundle)
                .navigation();
    }

    /**
     * 点击事件
     */
    public void setOnClickListener(View.OnClickListener clickListener, @IdRes int... ids){
        if(null != ids && null != clickListener){
            for (int id: ids) {
               findViewById(id).setOnClickListener(clickListener);
            }
        }
    }

    /**
     * post请求
     */
    public void post(String url, Observer observer){
        retrofitManage.execute(
                retrofitManage.getService(RetrofitApiService.class).onGetData(url)
                , observer);
    }
}
