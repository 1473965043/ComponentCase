package com.dianniu.carloan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dianniu.common.params.ARouterParams;
import com.dianniu.common.base.BaseActivity;
import com.dianniu.common.widget.bottomMenuLayout.BottomItemView;
import com.dianniu.common.widget.bottomMenuLayout.BottomMenuLayout;
import com.dianniu.main.ui.Frag;
import com.dianniu.main.ui.HomePageFrag;
import com.dianniu.mine.MineFrag;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterParams.APP_MAIN)
public class MainActivity extends BaseActivity {

    private List<BottomItemView> mList = new ArrayList<>();
    private String[] titles = {"首页", "发现", "我的"};
    /***
     * , "消息"
     * , R.mipmap.ic_launcher_round
     */
    private int[] unSelected = {
            R.drawable.home, R.drawable.discover, R.drawable.mine
    };

    private List<Fragment> fragmentList = new ArrayList<>();
    private BottomMenuLayout bottomMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomMenuLayout = findViewById(R.id.bottomView);
//        setOnClickListener(clickListener, R.id.bottomView);
        initFragment();
        initDatas();
        bindView();
    }

    private void bindView() {
        bottomMenuLayout.setDatas(this, R.id.content_container, mList);
        bottomMenuLayout.setCurrentFragment(0);
        bottomMenuLayout.setChangeListener(changeListener);
    }

    private void initDatas() {
        for(int i=0; i<titles.length; i++){
            BottomItemView itemView = new BottomItemView();
            itemView.setFragment(fragmentList.get(i));
            itemView.setIconId(unSelected[i]);
            itemView.setTitle(titles[i]);
            mList.add(itemView);
        }
    }

    private void initFragment() {
        fragmentList.add(new HomePageFrag());
        fragmentList.add(new MineFrag());
        fragmentList.add(new Frag());
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            executeARouter(ARouterParams.HOMEPAGE_MAIN);
        }
    };

    private BottomMenuLayout.OnChangeListener changeListener = new BottomMenuLayout.OnChangeListener() {
        @Override
        public void change(int position) {

        }
    };
}
