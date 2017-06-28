package com.yorhp.mlratingstudio.mvp.presenter;

import com.yorhp.mlratingstudio.mvp.model.entity.News;

import java.util.ArrayList;

/**
 * Created by Tyhj on 2017/6/28.
 */

public interface NewsListener {
    void newsGetOk(ArrayList<News> newsArrayList);
    void newsGetFail();
}
