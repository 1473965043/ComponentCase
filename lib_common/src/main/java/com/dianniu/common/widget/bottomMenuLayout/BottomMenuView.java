package com.dianniu.common.widget.bottomMenuLayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianniu.common.R;

import java.util.List;

/**
 * Created by guodong on 2018/4/16.
 */

public class BottomMenuView extends LinearLayout {

    private List<BottomItemView> mItemView;
    private FragmentManager fragmentManager;
    private Context mContext;
    private ViewPager viewPager;

    public BottomMenuView(Context context) {
        this(context, null);
    }

    public BottomMenuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void bindDatas(ViewPager viewPager, List<BottomItemView> itemView){
        this.viewPager = viewPager;
        this.mItemView = itemView;
        initItemView();
    }

    /**
     * 初始化BottomIteView
     */
    private void initItemView() {
        for(int i=0; i<mItemView.size(); i++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.bottommenu_layout, null);
            addItemView(i, view);
        }
    }

    /**
     * 添加itemView
     * @param position
     * @param view
     */
    private void addItemView(final int position, View view){
        ImageView img = view.findViewById(R.id.bottom_icon);
        TextView title = view.findViewById(R.id.bottom_title);
        img.setImageResource(mItemView.get(position).getIconId());
        title.setText(mItemView.get(position).getTitle());
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(position);
            }
        });
        /** 每一个Tab的布局参数 */
        LinearLayout.LayoutParams lp_tab = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        addView(view, position, lp_tab);
    }
}
