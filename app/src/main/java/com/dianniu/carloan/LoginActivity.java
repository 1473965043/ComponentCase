package com.dianniu.carloan;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dianniu.common.base.BaseActivity;
import com.dianniu.common.base.BindEventBus;
import com.dianniu.common.bean.MessageEvent;
import com.dianniu.common.params.ARouterParams;

@BindEventBus
@Route(path = ARouterParams.APP_LOGIN)
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onStickyEvent(MessageEvent event) {
        Log.w(getClass().getSimpleName(), event.getTag());
    }
}
