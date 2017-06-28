package com.yorhp.mlratingstudio.retrofite.convert;

import com.yorhp.mlratingstudio.mvp.model.entity.Recommend;

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

public class RecommendConvert implements Converter<ResponseBody, ArrayList<Recommend>> {
    public static final RecommendConvert INSTANCE = new RecommendConvert();
    @Override
    public ArrayList<Recommend> convert(ResponseBody value) throws IOException {
        String news = value.string();
        log("news",news);
        try {
            JSONObject jsonObject = new JSONObject(news);
            if (jsonObject.getInt("code") == 200) {
                ArrayList<Recommend> arrayList=new ArrayList<>();
                JSONArray array=jsonObject.getJSONArray("data");
                for (int i=0;i<array.length();i++){
                    int id=array.getJSONObject(i).getInt("id");
                    String name=array.getJSONObject(i).getString("name");
                    String image=array.getJSONObject(i).getString("image");
                    arrayList.add(new Recommend(id,name,image));
                }
                return arrayList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
