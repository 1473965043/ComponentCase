package com.jgd.mine;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jgd.common.base.BaseActivity;
import com.jgd.common.params.ARouterParams;

@Route(path = ARouterParams.MINE_MAIN)
public class MineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
    }
}
