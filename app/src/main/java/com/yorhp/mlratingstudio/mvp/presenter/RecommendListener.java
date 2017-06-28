package com.yorhp.mlratingstudio.mvp.presenter;

import com.yorhp.mlratingstudio.mvp.model.entity.Recommend;

import java.util.ArrayList;

/**
 * Created by Tyhj on 2017/6/28.
 */

public interface RecommendListener {
    void getRecommendOk(ArrayList<Recommend> recommends);
    void getRecommendFail();
}
