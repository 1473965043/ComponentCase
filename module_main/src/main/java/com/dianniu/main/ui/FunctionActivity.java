package com.dianniu.main.ui;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dianniu.common.base.BaseActivity;
import com.dianniu.common.bean.MessageEvent;
import com.dianniu.common.params.ARouterParams;
import com.dianniu.main.R;

import org.greenrobot.eventbus.EventBus;

@Route(path = ARouterParams.HOMEPAGE_MAIN)
public class FunctionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
    }

    public void toLogin(View view){
        executeARouter(ARouterParams.MINE_MAIN);
//        EventBus.getDefault().postSticky(new MessageEvent("粘性事件", "urgent"));
    }
}
