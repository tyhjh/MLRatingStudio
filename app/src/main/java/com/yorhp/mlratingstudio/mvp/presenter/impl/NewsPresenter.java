package com.yorhp.mlratingstudio.mvp.presenter.impl;

import com.yorhp.mlratingstudio.mvp.model.GetNews;

import javax.inject.Inject;

/**
 * Created by Tyhj on 2017/6/28.
 */

public class NewsPresenter {
    GetNews getNews;

    @Inject
    public NewsPresenter(GetNews getNews) {
        this.getNews = getNews;
    }

    public void getNews() {
        getNews.getNews();
    }

}
