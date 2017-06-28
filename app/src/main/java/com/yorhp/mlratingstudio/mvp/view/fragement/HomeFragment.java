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
import com.yorhp.mlratingstudio.dagger.component.DaggerHomeFrageComponent;
import com.yorhp.mlratingstudio.dagger.modules.HomeFrageModules;
import com.yorhp.mlratingstudio.mvp.model.entity.News;
import com.yorhp.mlratingstudio.mvp.model.entity.Recommend;
import com.yorhp.mlratingstudio.mvp.presenter.NewsListener;
import com.yorhp.mlratingstudio.mvp.presenter.RecommendListener;
import com.yorhp.mlratingstudio.mvp.presenter.impl.NewsPresenter;
import com.yorhp.mlratingstudio.mvp.presenter.impl.RecommendPresenter;
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

import javax.inject.Inject;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment implements NewsListener, RecommendListener {

    CoordinatorMenu coordingMenu;

    ArrayList<News> newsArrayList = new ArrayList<News>();
    ArrayList<Recommend> recommendArrayList = new ArrayList<Recommend>();

    ViewPager vp_news;

    FragmentPagerAdapter fragmentPagerAdapter;

    RecommendAdapter recommendAdapter;

    @Inject
    NewsPresenter newsPresenter;

    @Inject
    RecommendPresenter recommendPresenter;

    @ViewById
    PagerContainer container_news;

    @ViewById
    ImageView iv_homebg;


    @ViewById
    RecyclerView rcyl_recommend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHomeFrageComponent.builder()
                .homeFrageModules(new HomeFrageModules(this))
                .build().inject(this);
    }

    @AfterViews
    void afterViews() {
        //初始化顶部头像
        Glide.with(this).load("http://pic1.ytqmx.com:82/2015/0908/06/02.jpg!960.jpg").apply(GlideOption.getOption()).into(iv_homebg);
        initNews();
        initRecommend();
        newsPresenter.getNews();
        recommendPresenter.getRecommend();
    }

    private void initRecommend() {
        recommendAdapter = new RecommendAdapter(newsArrayList, getActivity());
        rcyl_recommend.setAdapter(recommendAdapter);
        rcyl_recommend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

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
                int firstVisible = lm.findFirstVisibleItemPosition();
                if (firstVisible == 0) {
                    coordingMenu.requestDisallowInterceptTouchEvent(false);
                } else {
                    coordingMenu.requestDisallowInterceptTouchEvent(true);
                }
            }
        });
    }

    @Click
    void iv_menu() {
        if (coordingMenu.isOpened())
            coordingMenu.closeMenu();
        else
            coordingMenu.openMenu();
    }

    @Click
    void iv_search() {
        startActivity(new Intent(getActivity(), VideoActivity_.class));
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
                NewsFragment fragment = new NewsFragment();
                if (newsArrayList != null && newsArrayList.size() > position) {
                    fragment.setAuction(newsArrayList.get(position));
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return newsArrayList.size();
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

    @Override
    public void newsGetOk(ArrayList<News> newsArrayList) {
        newsArrayList.addAll(newsArrayList);

        fragmentPagerAdapter.notifyDataSetChanged();
        vp_news.setCurrentItem(1);
    }

    @Override
    public void newsGetFail() {
        newsArrayList.add(new News(0,1,"不知道","不知道"));
        newsArrayList.add(new News(0,1,"不知道","不知道"));
        newsArrayList.add(new News(0,1,"不知道","不知道"));
        newsArrayList.add(new News(0,1,"不知道","不知道"));
        newsArrayList.add(new News(0,1,"不知道","不知道"));
        newsArrayList.add(new News(0,1,"不知道","不知道"));
        newsArrayList.add(new News(0,1,"不知道","不知道"));

        fragmentPagerAdapter.notifyDataSetChanged();
        vp_news.setCurrentItem(1);
    }


    @Override
    public void getRecommendOk(ArrayList<Recommend> recommends) {
        recommendArrayList.addAll(recommends);
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRecommendFail() {
        recommendArrayList.add(new Recommend(0,"不知道","不知道"));
        recommendArrayList.add(new Recommend(0,"不知道","不知道"));
        recommendArrayList.add(new Recommend(0,"不知道","不知道"));
        recommendArrayList.add(new Recommend(0,"不知道","不知道"));
        recommendArrayList.add(new Recommend(0,"不知道","不知道"));
        recommendArrayList.add(new Recommend(0,"不知道","不知道"));

        recommendAdapter.notifyDataSetChanged();
    }
}
