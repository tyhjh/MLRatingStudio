package com.yorhp.mlratingstudio.dagger.component;

import com.yorhp.mlratingstudio.dagger.modules.HomeFrageModules;
import com.yorhp.mlratingstudio.mvp.view.fragement.HomeFragment;

import dagger.Component;

/**
 * Created by Tyhj on 2017/6/28.
 */

@Component(modules = HomeFrageModules.class)
public interface HomeFrageComponent {
    void inject(HomeFragment homeFragment);
}
