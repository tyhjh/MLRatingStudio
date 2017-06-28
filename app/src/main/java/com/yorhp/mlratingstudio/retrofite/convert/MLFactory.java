package com.yorhp.mlratingstudio.retrofite.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static com.yorhp.mlratingstudio.app.MlApplication.log;

/**
 * Created by Tyhj on 2017/6/12.
 */

public class MLFactory extends Converter.Factory {
    public static final MLFactory INSTANCE = new MLFactory();

    public static MLFactory create() {
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        log("Type",type.toString());
        if(type.toString().equals("java.util.ArrayList<com.yorhp.mlratingstudio.mvp.model.entity.News>")){
            return NewsConvert.INSTANCE;
        }else if(type.toString().equals("java.util.ArrayList<com.yorhp.mlratingstudio.mvp.model.entity.Recommend>")){
            return RecommendConvert.INSTANCE;
        }else {
            return StringConvert.INSTANCE;
        }
    }
}
