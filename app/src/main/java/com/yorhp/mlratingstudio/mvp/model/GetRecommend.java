package com.yorhp.mlratingstudio.mvp.model;

import com.yorhp.mlratingstudio.mvp.model.entity.Recommend;
import com.yorhp.mlratingstudio.mvp.presenter.RecommendListener;
import com.yorhp.mlratingstudio.retrofite.MRetrofite;
import com.yorhp.mlratingstudio.retrofite.api.RecommendApi;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tyhj on 2017/6/28.
 */

public class GetRecommend {

    private RecommendListener listener;

    @Inject
    public GetRecommend(RecommendListener listener) {
        this.listener = listener;
    }

    public void getRecommend(){
        RecommendApi api= MRetrofite.getInstance().create(RecommendApi.class);
        api.getRecommend("token","sign","data")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Recommend>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Recommend> recommends) {
                        if(recommends!=null){
                            listener.getRecommendOk(recommends);
                        }else {
                            listener.getRecommendFail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.getRecommendFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
