package com.yorhp.mlratingstudio.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.mvp.model.entity.News;
import com.yorhp.mlratingstudio.util.image.GlideOption;
import com.yorhp.mlratingstudio.util.image.ImageUtil;
import com.yorhp.mlratingstudio.util.image.ShadowUtil;

import java.util.ArrayList;

/**
 * Created by Tyhj on 2017/6/22.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    ArrayList<News> arrayList;
    Context context;
    LayoutInflater inflater;

    public RecommendAdapter(ArrayList<News> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_recommend,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load("http://www.wine-world.com/UserFiles/ExImg/images/%E8%91%A1%E8%90%84%E9%85%92%E4%BA%A7%E9%87%8F%E4%BD%8E.jpg").apply(GlideOption.getOption()).into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        CardView card_shadow;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            card_shadow= (CardView) itemView.findViewById(R.id.card_shadow);
            ShadowUtil.getInstance(6,0).action(card_shadow);
            iv_image.setLayoutParams(new FrameLayout.LayoutParams((int)(ImageUtil.SCREEN_WIDTH*0.38), ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
}
