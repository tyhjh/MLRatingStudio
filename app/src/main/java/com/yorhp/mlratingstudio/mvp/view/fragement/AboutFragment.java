package com.yorhp.mlratingstudio.mvp.view.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.util.image.GlideOption;
import com.yorhp.mlratingstudio.util.image.ShadowUtil;


public class AboutFragment extends Fragment {


    private String auction;

    public void setAuction(String auction) {
        this.auction = auction;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_news, container, false);
        ImageView iv_news = (ImageView) rootView.findViewById(R.id.iv_news);
        ShadowUtil.getInstance(4,0).action(iv_news);
        if(auction!=null) {
            Glide.with(getActivity()).load("http://pic1.win4000.com/wallpaper/d/589c305d4ef12.jpg").apply(GlideOption.getOption()).into(iv_news);
        }
        return rootView;
    }

}
