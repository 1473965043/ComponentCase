package com.jgd.main.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgd.common.widget.SlideListenerScrollView;
import com.jgd.main.R;

/**
 * Created by guodong on 2018/4/16.
 */

public class HomePageFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_function, container, false);
        final View bar = contentView.findViewById(R.id.title_bar);
        final View tv = contentView.findViewById(R.id.tv_bar);
        SlideListenerScrollView scrollView = contentView.findViewById(R.id.content);
        scrollView.setScrollListener(new SlideListenerScrollView.ScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                Log.d(HomePageFrag.this.getClass().getSimpleName(), "onScrollChanged: " + l + "\n" + t + "\n" + oldl + "\n" + oldt);
                if(t >= tv.getMeasuredHeight()){

                }else{

                }
            }
        });
        return contentView;
    }
}
