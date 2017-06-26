package com.yorhp.mlratingstudio.app;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.imnjh.imagepicker.PickerConfig;
import com.imnjh.imagepicker.SImagePicker;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.util.image.GlideImageLoader;

import java.io.File;

/**
 * Created by Tyhj on 2017/5/23.
 */

public class MlApplication extends android.app.Application {
    private static MlApplication instance;
    public static final boolean ISDEBUG = true;

    public void onCreate() {
        super.onCreate();
        instance = this;
        initPicasso();
        initPickpicture();
    }

    //图象选择器初始化
    private void initPickpicture() {
        SImagePicker.init(new PickerConfig.Builder().setAppContext(this)
                .setImageLoader(new GlideImageLoader())
                .setToolbaseColor(getResources().getColor(R.color.colorPrimary))
                .build());
    }

    //Picasso初始化
    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this)
                .memoryCache(new LruCache(10 << 20))//设置内存缓存大小10M
                //.indicatorsEnabled(false) //设置左上角标记，主要用于测试
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    //文件夹初始化
    public void initDir(String name) {
        File f1 = new File(Environment.getExternalStorageDirectory() + name);
        if (!f1.exists()) {
            f1.mkdirs();
        }
    }

    //打印初始化
    public static void log(String key, String value) {
        if (ISDEBUG)
            Log.e(key, value);
    }

    public static Context getAppContext() {
        return instance;
    }
}
