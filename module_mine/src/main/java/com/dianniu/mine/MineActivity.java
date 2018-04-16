package com.dianniu.mine;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dianniu.common.base.BaseActivity;
import com.dianniu.common.params.ARouterParams;

@Route(path = ARouterParams.MINE_MAIN)
public class MineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
    }
}
