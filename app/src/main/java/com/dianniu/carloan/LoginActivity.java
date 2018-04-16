package com.dianniu.carloan;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dianniu.common.base.BaseActivity;
import com.dianniu.common.base.BindEventBus;
import com.dianniu.common.bean.MessageEvent;
import com.dianniu.common.params.ARouterParams;
import com.dianniu.common.widget.ForbidSlideViewPager;
import com.dianniu.common.widget.bottomMenuLayout.BottomItemView;
import com.dianniu.common.widget.bottomMenuLayout.BottomMenuView;
import com.dianniu.main.ui.Frag;
import com.dianniu.main.ui.HomePageFrag;
import com.dianniu.mine.MineFrag;

import java.util.ArrayList;
import java.util.List;

@BindEventBus
@Route(path = ARouterParams.APP_LOGIN)
public class LoginActivity extends BaseActivity {

    private List<BottomItemView> itemViews = new ArrayList<>();
    private String[] titles = {"首页", "发现", "我的"};
    private int[] unSelected = {
            R.drawable.home, R.drawable.discover, R.drawable.mine
    };
    private ForbidSlideViewPager viewPager;
    private BottomMenuView bottomMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewPager = findViewById(R.id.viewpager);
        bottomMenuView = findViewById(R.id.bottomView);
        initDatas();
        bindDatas();
    }

    private void bindDatas() {
        viewPager.setAdapter(new ViewPagerAdapter(
                getSupportFragmentManager()
                , new HomePageFrag()
                , new MineFrag()
                , new Frag()));
        bottomMenuView.bindDatas(viewPager, itemViews);
    }

    private void initDatas() {
        for (int i = 0; i < titles.length; i++) {
            BottomItemView itemView = new BottomItemView();
            itemView.setIconId(unSelected[i]);
            itemView.setTitle(titles[i]);
            itemViews.add(itemView);
        }
    }

    @Override
    public void onStickyEvent(MessageEvent event) {
        Log.w(getClass().getSimpleName(), event.getTag());
    }
}
