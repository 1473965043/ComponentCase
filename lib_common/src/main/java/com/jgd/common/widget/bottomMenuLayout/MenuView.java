package com.jgd.common.widget.bottomMenuLayout;

/**
 * Created by guodong on 2017/2/28.
 */

public class MenuView {

    private int iconId;
    private String title;

    public MenuView(){}

    public MenuView(int iconId, String title){
        this.iconId = iconId;
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
