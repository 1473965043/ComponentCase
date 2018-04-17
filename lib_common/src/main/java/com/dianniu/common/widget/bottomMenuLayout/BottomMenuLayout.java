package com.dianniu.common.widget.bottomMenuLayout;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dianniu.common.R;
import com.dianniu.common.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guodong on 2016/11/24.
 * 自定义底部菜单栏,可以自带fragment
 */

public class BottomMenuLayout extends RelativeLayout {

    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private Context mContext;
    private OnChangeListener mChangeListener;
    private int current;
    private FrameLayout frameLayout;
    private LinearLayout bottomLayout;
    private View line;

    public interface OnChangeListener{
        void change(int position);
    }

    public void setChangeListener(OnChangeListener listener){
        mChangeListener = listener;
    }

    /**
     * 构造方法-->new出来时调用
     * @param context
     */
    public BottomMenuLayout(Context context) {
        this(context, null);
    }

    /**
     * 构造方法-->布局文件中调用
     * @param context
     * @param attrs
     */
    public BottomMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        fragmentManager = ((AppCompatActivity)ViewUtil.getActivity(this)).getSupportFragmentManager();
        fragments = new ArrayList<>();
        frameLayout = new FrameLayout(mContext);
        bottomLayout = new LinearLayout(mContext);
        line = new View(mContext);
        line.setId(R.id.line);
        bottomLayout.setId(R.id.menuBarView);
        LayoutParams bottomLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        bottomLp.addRule(ALIGN_PARENT_BOTTOM);
        bottomLayout.setLayoutParams(bottomLp);
        LayoutParams lineLp = new LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.y1));
        lineLp.addRule(ABOVE, bottomLayout.getId());
        line.setLayoutParams(lineLp);
        frameLayout.setId(R.id.fragmentContainer);
        LayoutParams fragmentLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        fragmentLp.addRule(ABOVE, line.getId());
        fragmentLp.addRule(ALIGN_PARENT_TOP);
        frameLayout.setLayoutParams(fragmentLp);
        line.setBackgroundColor(Color.parseColor("#EBEBEC"));
        addView(frameLayout);
        addView(bottomLayout);
        addView(line);
    }

    /**
     * 绑定数据
     * @param itemViews 菜单数据
     */
    public void bindDatas(MenuView... itemViews){
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
                    setCurrentFragment(position);
                    if(null != mChangeListener){
                        mChangeListener.change(position);
                    }
                }
            });
            /** 每一个Tab的布局参数 */
            LinearLayout.LayoutParams lp_tab = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            bottomLayout.addView(view, position, lp_tab);
        }
    }

    /**
     * 绑定fragment
     * @param fragments
     */
    public void bindFragments(Fragment... fragments){
        for (Fragment fragment : fragments) {
            this.fragments.add(fragment);
            fragmentManager.beginTransaction().add(frameLayout.getId(), fragment).hide(fragment).commit();
        }
        setCurrentFragment(0);
    }

//    /**
//     * 更新UI
//     * @param current
//     */
//    private void notifyDataSetChanged(int current){
//        for(int i=0; i<getChildCount(); i++){
//            if(current == i){
//                getChildAt(i).setSelected(true);
//                getChildAt(i).findViewById(R.id.bottom_icon).setSelected(true);
//                getChildAt(i).findViewById(R.id.bottom_title).setSelected(true);
//            }else{
//                getChildAt(i).setSelected(false);
//                getChildAt(i).findViewById(R.id.bottom_icon).setSelected(false);
//                getChildAt(i).findViewById(R.id.bottom_title).setSelected(false);
//            }
//        }
//    }

    /**
     * 设置当前显示的fragment
     */
    public void setCurrentFragment(int current){
        for(int i=0; i<fragments.size(); i++){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Fragment fragment = fragments.get(i);
            if(i == current){
                ft.show(fragment);
            }else{
                ft.hide(fragment);
            }
            ft.commit();
        }
        this.current = current;
//        notifyDataSetChanged(current);
    }

    /**
     * 获取当前fragment的下标
     * @return
     */
    public int getCurrent(){
        return this.current;
    }
}
