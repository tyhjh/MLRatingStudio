package com.yorhp.mlratingstudio.retrofite.api;

import com.yorhp.mlratingstudio.mvp.model.entity.News;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Tyhj on 2017/6/28.
 */

public interface NewsApi {
    @GET("news")
    Observable<ArrayList<News>> getHome(@Header("token") String token, @Query("sign") String sign, @Query("data") String data);
}
