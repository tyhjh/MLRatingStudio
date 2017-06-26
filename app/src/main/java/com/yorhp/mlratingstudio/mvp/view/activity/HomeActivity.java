package com.yorhp.mlratingstudio.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.app.BaseActivity;
import com.yorhp.mlratingstudio.mvp.view.fragement.HomeFragment_;
import com.yorhp.mlratingstudio.mvp.view.fragement.LearnFragment_;
import com.yorhp.mlratingstudio.mvp.view.fragement.OurFragment_;
import com.yorhp.mlratingstudio.mvp.view.fragement.ShopFragment_;
import com.yorhp.mlratingstudio.mvp.view.myview.CoordinatorMenu;
import com.yorhp.mlratingstudio.mvp.view.myview.NoScrollPager;
import com.yorhp.mlratingstudio.util.image.GlideOption;
import com.yorhp.mlratingstudio.util.image.ShadowUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;


@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements CoordinatorMenu.OnMenuChangeListener {

    HomeFragment_ home;

    @ViewById
    CoordinatorMenu cording_meun;


    @ViewById
    ImageView iv_home, iv_learn, iv_our, iv_shop;

    @ViewById
    CircleImageView iv_head;

    @ViewById
    TextView tv_home, tv_learn, tv_our, tv_shop;

    @ViewById
    TextView tv_signin;

    @ViewById
    NoScrollPager vp_home;

    @AfterViews
    void afterViews() {
        initCoordingMenu();
        initBottomBar(0);
        initViewPager();
    }

    @Click()
    void ll_home() {
        vp_home.setCurrentItem(0);
    }

    @Click
    void ll_learn() {
        vp_home.setCurrentItem(1);
    }

    @Click
    void ll_our() {
        vp_home.setCurrentItem(2);
    }

    @Click
    void ll_shop() {
        vp_home.setCurrentItem(3);
    }

    //初始化viewPager
    private void initViewPager() {
        vp_home.setCurrentItem(0);
        vp_home.setOffscreenPageLimit(4);
        vp_home.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return home;
                    case 1:
                        return new LearnFragment_();
                    case 2:
                        return new OurFragment_();
                    case 3:
                        return new ShopFragment_();
                    default:
                        return home;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initBottomBar(position);
                if (position == 0) {
                    setStatusBarFontDark(false);
                } else {
                    setStatusBarFontDark(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        home.setCoordingMenu(cording_meun);
    }

    //初始化bottomBar
    private void initBottomBar(int select) {
        Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_home);
        Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_learn);
        Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_our);
        Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_shop);
        tv_home.setTextColor(getResources().getColor(R.color.gray));
        tv_learn.setTextColor(getResources().getColor(R.color.gray));
        tv_our.setTextColor(getResources().getColor(R.color.gray));
        tv_shop.setTextColor(getResources().getColor(R.color.gray));
        switch (select) {
            case 0:
                Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_home);
                tv_home.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 1:
                Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_learn);
                tv_learn.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 2:
                Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_our);
                tv_our.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 3:
                Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_shop);
                tv_shop.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    //初始化个人信息
    private void initCoordingMenu() {
        cording_meun.setListener(this);
        Glide.with(this).load(R.mipmap.ic_launcher).apply(GlideOption.getOption()).into(iv_head);
        ShadowUtil.getInstance(1, 50).action(tv_signin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        home = new HomeFragment_();
    }

    long currentTime = 0;

    @Override
    public void onBackPressed() {
        if (cording_meun.isOpened())
            cording_meun.closeMenu();
        else if (System.currentTimeMillis() - currentTime > 2000) {
            currentTime = System.currentTimeMillis();
            toast("再次点击退出");
        } else
            super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //监听菜单的状态
    @Override
    public void menuChange(boolean isOpen) {
        if (isOpen)
            setStatusBarFontDark(true);
        else if (vp_home.getCurrentItem() == 0)
            setStatusBarFontDark(false);
    }
}
