package com.jgd.main.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jgd.common.base.BaseActivity;
import com.jgd.common.params.ARouterParams;
import com.jgd.main.R;

@Route(path = ARouterParams.HOMEPAGE_MAIN)
public class FunctionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
    }

    public void toLogin(){
        executeARouter(ARouterParams.MINE_MAIN);
//        EventBus.getDefault().postSticky(new MessageEvent("粘性事件", "urgent"));
    }
}
