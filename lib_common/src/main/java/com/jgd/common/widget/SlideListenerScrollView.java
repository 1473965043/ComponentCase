package com.jgd.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by guodong on 2018/4/28.
 * 自定义ScrollView带滚动监听
 */

public class SlideListenerScrollView extends ScrollView {

    public interface ScrollListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    private ScrollListener scrollListener;

    public void setScrollListener(ScrollListener scrollListener){
        this.scrollListener = scrollListener;
    }

    public SlideListenerScrollView(Context context) {
        this(context, null);
    }

    public SlideListenerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(this.scrollListener != null){
            this.scrollListener.onScrollChanged(l, t, oldl, oldt);
        }
    }
}
