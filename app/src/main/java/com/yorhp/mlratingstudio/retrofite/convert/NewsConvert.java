package com.yorhp.mlratingstudio.retrofite.convert;

import com.yorhp.mlratingstudio.mvp.model.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import static com.yorhp.mlratingstudio.app.MlApplication.log;

/**
 * Created by Tyhj on 2017/6/28.
 */

public class NewsConvert implements Converter<ResponseBody, ArrayList<News>> {
    public static final NewsConvert INSTANCE = new NewsConvert();
    @Override
    public ArrayList<News> convert(ResponseBody value) throws IOException {
        String msg = value.string();
        log("news",msg);
        try {
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getInt("code") == 200) {
                ArrayList<News> newses=new ArrayList<>();
                JSONArray array=jsonObject.getJSONArray("data");
                for(int i=0;i<array.length();i++){
                    int id=array.getJSONObject(i).getInt("id");
                    int tag=array.getJSONObject(i).getInt("tag");
                    String title=array.getJSONObject(i).getString("title");
                    String image=array.getJSONObject(i).getString("image");
                    News news=new News(id,tag,title,image);
                    newses.add(news);
                }
                return newses;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
