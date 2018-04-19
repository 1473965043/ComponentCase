package com.jgd.module;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jgd.common.params.ARouterParams;
import com.jgd.common.base.BaseActivity;
import com.jgd.common.widget.bottomMenuLayout.MenuView;
import com.jgd.common.widget.bottomMenuLayout.BottomMenuLayout;
import com.jgd.main.ui.Frag;
import com.jgd.main.ui.HomePageFrag;
import com.jgd.mine.MineFrag;

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
