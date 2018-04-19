package com.jgd.common.widget.bottomMenuLayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jgd.common.R;
import com.jgd.common.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guodong on 2018/4/16.
 * 自定义底部菜单栏,可以自带viewpager
 */

public class BottomMenuBar extends RelativeLayout {

    private List<Fragment> fragments;
    private Context mContext;
    private ViewPager viewPager;
    private LinearLayout bottomLayout;
    private View line;

    public BottomMenuBar(Context context) {
        this(context, null);
    }

    public BottomMenuBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMenuBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        fragments = new ArrayList<>();
        line = new View(context);
        bottomLayout = new LinearLayout(context);
        bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams bottomLayoutLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        bottomLayoutLp.addRule(ALIGN_PARENT_BOTTOM);
        bottomLayout.setLayoutParams(bottomLayoutLp);
        bottomLayout.setId(R.id.menuBarView);
        LayoutParams lineLp = new LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.y2));
        lineLp.addRule(ABOVE, bottomLayout.getId());
        line.setId(R.id.line);
        line.setLayoutParams(bottomLayoutLp);
    }

    /**
     * 绑定数据
     * @param viewPager 传入自定义要求的viewpager
     * @param itemViews item数据
     */
    public BottomMenuBar bindDatas(ViewPager viewPager, MenuView... itemViews){
        this.viewPager = viewPager;
        bindDatas(itemViews);
        return this;
    }

    /**
     * 绑定数据
     * @param itemViews item数据
     */
    public BottomMenuBar bindDatas(MenuView... itemViews){
        layoutItemView();
        addItemView(itemViews);
        AppCompatActivity appCompatActivity = (AppCompatActivity) ViewUtil.getActivity(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter(appCompatActivity.getSupportFragmentManager());
        setViewPagerAdapter(adapter);
        return this;
    }

    /**
     * 绑定fragment
     * @param fragments
     * @return
     * 自定义添加适配器不需要调用这个方法
     */
    public BottomMenuBar bindFragments(Fragment... fragments){
        this.fragments = new ArrayList<>();
        for (Fragment fragment: fragments) {
            this.fragments.add(fragment);
        }
        if(null != viewPager && null != viewPager.getAdapter()){
            viewPager.getAdapter().notifyDataSetChanged();
        }
        return this;
    }

    /**
     * 给viewpager添加适配器
     * @param adapter
     */
    public void setViewPagerAdapter(PagerAdapter adapter){
        viewPager.setAdapter(adapter);
    }

    /**
     * 布局子控件
     */
    private void layoutItemView() {
        if(null == viewPager){
            viewPager = new ViewPager(mContext);
        }
        LayoutParams viewPagerLp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        viewPagerLp.addRule(ALIGN_PARENT_TOP);
        viewPagerLp.addRule(ABOVE, line.getId());
        viewPager.setId(R.id.menuViewPager);
        viewPager.setLayoutParams(viewPagerLp);
        addView(bottomLayout);
        addView(viewPager);
        addView(line);
    }

    /**
     * 初始化BottomIteView, 添加itemView
     */
    private void addItemView(MenuView... itemViews) {
        for(int i=0; i<itemViews.length; i++){
            final int position = i;
            View view = LayoutInflater.from(mContext).inflate(R.layout.bottommenu_layout, null);
            ImageView img = view.findViewById(R.id.bottom_icon);
            TextView title = view.findViewById(R.id.bottom_title);
            img.setImageResource(itemViews[position].getIconId());
            title.setText(itemViews[position].getTitle());
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(position, false);
                }
            });
            /** 每一个Tab的布局参数 */
            LinearLayout.LayoutParams lp_tab = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            bottomLayout.addView(view, position, lp_tab);
        }
    }

    /**
     * 默认viewpager适配器
     */
    public class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
