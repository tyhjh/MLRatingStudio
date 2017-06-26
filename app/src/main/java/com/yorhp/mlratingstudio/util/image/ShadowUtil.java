package com.yorhp.mlratingstudio.util.image;

import android.graphics.Color;

import com.hitomi.cslibrary.CrazyShadow;
import com.hitomi.cslibrary.base.CrazyShadowDirection;
import com.yorhp.mlratingstudio.app.MlApplication;
import com.yorhp.mlratingstudio.util.convert.ConvertUtil;

/**
 * Created by Tyhj on 2017/6/22.
 */

public class ShadowUtil {
    public static CrazyShadow.Builder getInstance(int shadow,int corner){
        return new CrazyShadow.Builder()
                .setContext(MlApplication.getAppContext())
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(ConvertUtil.dp2px(shadow))
                .setCorner(corner)
                .setBackground(Color.parseColor("#96a993"))
                .setImpl(CrazyShadow.IMPL_DRAW);
    }
}
