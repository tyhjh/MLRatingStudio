package com.yorhp.mlratingstudio.mvp.view.fragement;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.mvp.view.activity.VideoActivity_;
import com.yorhp.mlratingstudio.mvp.view.adapter.RecommendAdapter;
import com.yorhp.mlratingstudio.mvp.view.myview.CoordinatorMenu;
import com.yorhp.mlratingstudio.util.image.GlideOption;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment {

    CoordinatorMenu coordingMenu;

    ArrayList<String> aray = new ArrayList<>();

    ViewPager vp_news;

    FragmentPagerAdapter fragmentPagerAdapter;

    RecommendAdapter recommendAdapter;

    @ViewById
    PagerContainer container_news;

    @ViewById
    ImageView iv_homebg;


    @ViewById
    RecyclerView rcyl_recommend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews() {
        Glide.with(this).load("http://pic1.ytqmx.com:82/2015/0908/06/02.jpg!960.jpg").apply(GlideOption.getOption()).into(iv_homebg);
        initNews();
        initRecommend();
        updateNews();
    }

    private void initRecommend() {
        recommendAdapter=new RecommendAdapter(aray,getActivity());
        rcyl_recommend.setAdapter(recommendAdapter);
        rcyl_recommend.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        rcyl_recommend.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                coordingMenu.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        rcyl_recommend.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisible=lm.findFirstVisibleItemPosition();
                if(firstVisible==0){
                    coordingMenu.requestDisallowInterceptTouchEvent(false);
                }else {
                    coordingMenu.requestDisallowInterceptTouchEvent(true);
                }
            }
        });
    }

    @Click
    void iv_menu(){
        if(coordingMenu.isOpened())
            coordingMenu.closeMenu();
        else
            coordingMenu.openMenu();
    }

    @Click
    void iv_search(){
        startActivity(new Intent(getActivity(), VideoActivity_.class));
    }

    //更新news
    @UiThread()
    void updateNews() {
        aray.add("sss");
        aray.add("sss");
        aray.add("sss");
        aray.add("sss");
        aray.add("sss");
        aray.add("sss");
        aray.add("sss");
        aray.add("sss");
        fragmentPagerAdapter.notifyDataSetChanged();
        vp_news.setCurrentItem(1);
        recommendAdapter.notifyDataSetChanged();
    }

    //初始化news
    @UiThread
    void initNews() {
        vp_news = container_news.getViewPager();
        vp_news.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (vp_news.getCurrentItem() == 0) {
                    coordingMenu.requestDisallowInterceptTouchEvent(false);
                } else
                    coordingMenu.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        fragmentPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                OverlapFragment fragment = new OverlapFragment();
                if (aray != null && aray.size() > position) {
                    fragment.setAuction(aray.get(position));
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return aray.size();
            }
        };
        vp_news.setAdapter(fragmentPagerAdapter);
        new CoverFlow.Builder().with(vp_news)
                .scale(0.08f)
                .pagerMargin(getResources().getDimensionPixelSize(R.dimen.overlap_pager_margin))
                .spaceSize(0f)
                .build();
        vp_news.setOffscreenPageLimit(5);
    }

    public void setCoordingMenu(CoordinatorMenu coordingMenu) {
        this.coordingMenu = coordingMenu;
    }
}
