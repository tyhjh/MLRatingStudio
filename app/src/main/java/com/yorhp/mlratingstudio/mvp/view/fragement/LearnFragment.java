package com.yorhp.mlratingstudio.mvp.view.fragement;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.util.common.DataCleanUitl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_learn)
public class LearnFragment extends Fragment {

    @ViewById
    TextView tv_cache;


    @Click
    void tv_cache(){
        DataCleanUitl.clearAllCache(getActivity());
        afterView();
    }


    @AfterViews
    void afterView(){
        try {
            tv_cache.setText(DataCleanUitl.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
