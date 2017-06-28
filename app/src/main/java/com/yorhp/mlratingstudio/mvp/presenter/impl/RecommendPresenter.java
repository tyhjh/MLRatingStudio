package com.yorhp.mlratingstudio.mvp.presenter.impl;

import com.yorhp.mlratingstudio.mvp.model.GetRecommend;

import javax.inject.Inject;

/**
 * Created by Tyhj on 2017/6/28.
 */

public class RecommendPresenter {
    GetRecommend getRecommend;

    @Inject
    public RecommendPresenter(GetRecommend getRecommend) {
        this.getRecommend = getRecommend;
    }
    public void getRecommend(){
        getRecommend.getRecommend();
    }
}
