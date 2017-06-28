package com.yorhp.mlratingstudio.retrofite;

import com.yorhp.mlratingstudio.app.MlApplication;
import com.yorhp.mlratingstudio.retrofite.convert.MLFactory;
import com.yorhp.mlratingstudio.util.internet.InternetUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Tyhj on 2017/6/19.
 */

public class MRetrofite {
    private volatile static Retrofit instance=null;

    public static Retrofit getInstance(){

        if(instance==null){
            synchronized (MRetrofite.class){
                if(instance==null){

                    //cache url
                    File httpCacheDirectory = new File(MlApplication.getAppContext().getCacheDir(), "responses");

                    int cacheSize = 50 * 1024 * 1024; // 10 MiB
                    Cache cache = new Cache(httpCacheDirectory, cacheSize);
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(getInterceptor())
                            .addNetworkInterceptor(getNetWorkInterceptor())
                            .cache(cache).build();

                    instance=new Retrofit
                            .Builder()
                            .baseUrl("http://www.tyhj5.com/wallPaper/")
                            .client(client)
                            .addConverterFactory(MLFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return instance;
    }

    /**
     * 设置返回数据的  Interceptor  判断网络   没网读取缓存
     */
    public static Interceptor getInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!InternetUtil.isOnline()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }


    /**
     * 设置连接器  设置缓存
     */
    public static Interceptor getNetWorkInterceptor (){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (InternetUtil.isOnline()) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为1周
                    int maxStale = 60 * 60 * 24 * 7;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }

}
