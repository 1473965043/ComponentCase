package com.jgd.common.bean;

/**
 * Created by guodong on 2018/4/16.
 */

public class MessageEvent {

    public String tag;
    public Object obj;

    public MessageEvent(String tag, Object obj) {
        this.tag = tag;
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        tag = tag;
    }
}
