package com.dianniu.carloan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dianniu.common.params.ARouterParams;
import com.dianniu.common.base.BaseActivity;
import com.dianniu.common.widget.bottomMenuLayout.MenuView;
import com.dianniu.common.widget.bottomMenuLayout.BottomMenuLayout;
import com.dianniu.main.ui.Frag;
import com.dianniu.main.ui.HomePageFrag;
import com.dianniu.mine.MineFrag;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterParams.APP_MAIN)
public class MainActivity extends BaseActivity {

    private BottomMenuLayout bottomMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomMenuLayout = findViewById(R.id.bottomView);
        bindView();
    }

    private void bindView() {
        bottomMenuLayout.bindDatas(
                new MenuView(R.drawable.home_selected, "首页")
                , new MenuView(R.drawable.discover_selected, "发现")
                , new MenuView(R.drawable.mine_selected, "我的")
        );
        bottomMenuLayout.bindFragments(
                new HomePageFrag()
                , new MineFrag()
                , new Frag()
        );
    }
}
