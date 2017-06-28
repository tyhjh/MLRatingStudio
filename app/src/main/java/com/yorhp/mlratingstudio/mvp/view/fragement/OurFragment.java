package com.yorhp.mlratingstudio.mvp.view.fragement;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LinkagePager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.mvp.view.myview.CoordinatorMenu;
import com.yorhp.mlratingstudio.mvp.view.myview.NoScrollLinkagePaper;
import com.yorhp.mlratingstudio.util.image.GlideOption;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import me.crosswall.lib.coverflow.core.LinkageCoverTransformer;
import me.crosswall.lib.coverflow.core.LinkagePagerContainer;


@EFragment(R.layout.fragment_our)
public class OurFragment extends Fragment {

    FragmentPagerAdapter headPagerAdapter;
    FragmentPagerAdapter aboutPagerAdapter;

    ArrayList<String> aray = new ArrayList<>();
    CoordinatorMenu cording_meun;

    LinkagePager vp_about;

    NoScrollLinkagePaper vp_head;

    @ViewById
    LinkagePagerContainer container_about, container_head;

    @ViewById
    ImageView iv_image, iv_wine, iv_studio;

    @ViewById
    ImageView iv_menu, iv_search, iv_pre, iv_next;

    @AfterViews
    void afterViews() {
        Glide.with(getActivity()).load("http://a1.att.hudong.com/01/30/19300001117700132929307395223.jpg").apply(GlideOption.getOption()).into(iv_wine);
        Glide.with(getActivity()).load("http://www.dfsh99.com//UploadFile/2014111957468345.jpg").apply(GlideOption.getOption()).into(iv_studio);
        initHead();
        initAbout();
        updateNews();
        initImage();
    }

    @Click
    void iv_pre() {
        if (vp_about.getCurrentItem() != 0) {
            vp_about.setCurrentItem(vp_about.getCurrentItem() - 1);
            vp_head.setCurrentItem(vp_about.getCurrentItem() - 1);
        }
    }

    @Click
    void iv_next() {
        if (vp_about.getCurrentItem() < vp_about.getChildCount()) {
            vp_about.setCurrentItem(vp_about.getCurrentItem() + 1);
            vp_head.setCurrentItem(vp_about.getCurrentItem() + 1);
        }
    }

    private void initImage() {
        vp_about.addOnPageChangeListener(new LinkagePager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Glide.with(getActivity()).load(aray.get(i)).apply(GlideOption.getOption()).into(iv_image);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        vp_head.addOnPageChangeListener(new LinkagePager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Glide.with(getActivity()).load(aray.get(i)).apply(GlideOption.getOption()).into(iv_image);
                if (vp_about.getCurrentItem() != i) {
                    vp_about.setCurrentItem(i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initAbout() {
        vp_about = container_about.getViewPager();
        vp_about.setAdapter(aboutPagerAdapter);
        vp_about.setPageTransformer(false, new LinkageCoverTransformer(0f, 55f, 0f, 0f));
        vp_about.setOffscreenPageLimit(5);
        vp_about.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cording_meun.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void initHead() {
        vp_head = (NoScrollLinkagePaper) container_head.getViewPager();
        vp_head.setAdapter(headPagerAdapter);
        vp_head.setPageTransformer(false, new LinkageCoverTransformer(0.3f, 55f, 0f, 0f));
        vp_head.setOffscreenPageLimit(5);
        vp_head.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cording_meun.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        container_head.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cording_meun.requestDisallowInterceptTouchEvent(true);
                return true;
            }
        });
    }

    //更新news
    @UiThread()
    void updateNews() {
        vp_about.setLinkagePager(vp_head);
        vp_head.setLinkagePager(vp_about);
        aray.add("http://ac-fgtnb2h8.clouddn.com/4381a95edb903f88f0bf.jpg");
        aray.add("http://ac-fgtnb2h8.clouddn.com/39dbac52c49bf3a535cb.jpg");
        aray.add("http://ac-fgtnb2h8.clouddn.com/0a6fa1ede06bddc9c3f0.jpg");
        aray.add("http://ac-fgtnb2h8.clouddn.com/97bd227bf380ad3be0fe.jpg");
        aray.add("http://ac-fgtnb2h8.clouddn.com/aa71168e31ee92ddeed5.jpg");
        aray.add("http://ac-fgtnb2h8.clouddn.com/8ec533ffdb1f04844653.jpg");
        aray.add("http://ac-fgtnb2h8.clouddn.com/77350ef53cc72b24c9a8.jpg");
        aray.add("http://ac-fgtnb2h8.clouddn.com/20c855dc058b48a0cf90.jpg");
        headPagerAdapter.notifyDataSetChanged();
        aboutPagerAdapter.notifyDataSetChanged();
        vp_head.setCurrentItem(1);
        vp_about.setCurrentItem(1);
    }

    public void setCoordingMenu(CoordinatorMenu cording_meun) {
        this.cording_meun = cording_meun;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                HeadFragment fragment = new HeadFragment();
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
        aboutPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                AboutFragment fragment = new AboutFragment();
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
    }


}
