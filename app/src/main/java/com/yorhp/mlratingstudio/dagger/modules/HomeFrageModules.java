package com.yorhp.mlratingstudio.dagger.modules;

import com.yorhp.mlratingstudio.mvp.presenter.NewsListener;
import com.yorhp.mlratingstudio.mvp.presenter.RecommendListener;
import com.yorhp.mlratingstudio.mvp.view.fragement.HomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tyhj on 2017/6/28.
 */

@Module
public class HomeFrageModules {
    NewsListener newsListener;
    RecommendListener recommendListener;

    public HomeFrageModules(HomeFragment homeFragment) {
        this.newsListener = homeFragment;
        this.recommendListener=homeFragment;
    }

    @Provides
    NewsListener newsListenerProvider(){
        return newsListener;
    }

    @Provides
    RecommendListener recommendListenerProvider(){
        return recommendListener;
    }

}
