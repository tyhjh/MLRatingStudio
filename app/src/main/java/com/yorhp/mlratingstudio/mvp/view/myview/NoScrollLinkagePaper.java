package com.yorhp.mlratingstudio.mvp.view.myview;

import android.content.Context;
import android.support.v4.view.LinkagePager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Tyhj on 2017/6/27.
 */

public class NoScrollLinkagePaper extends LinkagePager{

    private boolean isPagingEnabled = false;

    public NoScrollLinkagePaper(Context context) {
        super(context);
    }

    public NoScrollLinkagePaper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }


}
