package com.yorhp.mlratingstudio.mvp.view.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.util.image.GlideOption;

import de.hdodenhof.circleimageview.CircleImageView;


//头像列表
public class HeadFragment extends Fragment {


    private String auction;

    public void setAuction(String auction) {
        this.auction = auction;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_head, container, false);
        CircleImageView iv_news = (CircleImageView) rootView.findViewById(R.id.iv_head);
        if(auction!=null) {
            Glide.with(getActivity()).load(auction).apply(GlideOption.getOption()).into(iv_news);
        }
        return rootView;
    }

}
