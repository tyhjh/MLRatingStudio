package com.yorhp.mlratingstudio.mvp.model;

import com.yorhp.mlratingstudio.mvp.model.entity.News;
import com.yorhp.mlratingstudio.mvp.presenter.NewsListener;
import com.yorhp.mlratingstudio.retrofite.MRetrofite;
import com.yorhp.mlratingstudio.retrofite.api.NewsApi;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tyhj on 2017/6/28.
 */

public class GetNews {
    private NewsListener listener;

    @Inject
    public GetNews(NewsListener listener) {
        this.listener = listener;
    }

    public void getNews(){
        NewsApi newsApi= MRetrofite.getInstance().create(NewsApi.class);
        newsApi.getHome("token","sign","data")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<News> newsArrayList) {
                        if(newsArrayList!=null)
                            listener.newsGetOk(newsArrayList);
                        else
                            listener.newsGetFail();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.newsGetFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
