package com.dianniu.carloan;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dianniu.common.base.BaseActivity;
import com.dianniu.common.base.BindEventBus;
import com.dianniu.common.bean.MessageEvent;
import com.dianniu.common.params.ARouterParams;
import com.dianniu.common.widget.bottomMenuLayout.MenuView;
import com.dianniu.common.widget.bottomMenuLayout.BottomMenuBar;
import com.dianniu.main.ui.Frag;
import com.dianniu.main.ui.HomePageFrag;
import com.dianniu.mine.MineFrag;

@BindEventBus
@Route(path = ARouterParams.APP_LOGIN)
public class LoginActivity extends BaseActivity {

    private BottomMenuBar bottomMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bottomMenuView = findViewById(R.id.content_container);
        bindDatas();
    }

    private void bindDatas() {
        bottomMenuView.bindFragments(
                  new HomePageFrag()
                , new MineFrag()
                , new Frag()
        );
        bottomMenuView.bindDatas(
                  new MenuView(R.drawable.home_selected, "首页")
                , new MenuView(R.drawable.discover_selected, "发现")
                , new MenuView(R.drawable.mine_selected, "我的")
        );
    }

    @Override
    public void onStickyEvent(MessageEvent event) {
        Log.w(getClass().getSimpleName(), event.getTag());
    }
}
