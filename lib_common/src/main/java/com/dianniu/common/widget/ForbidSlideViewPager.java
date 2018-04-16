package com.dianniu.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by guodong on 2018/4/16.
 * 可以设置禁止滑动的viewpager
 */

public class ForbidSlideViewPager extends ViewPager {

    public ForbidSlideViewPager(Context context) {
        this(context, null);
    }

    public ForbidSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false && super.onInterceptTouchEvent(event);
    }

}
