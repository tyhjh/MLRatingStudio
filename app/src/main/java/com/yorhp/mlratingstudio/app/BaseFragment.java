package com.yorhp.mlratingstudio.app;


import android.support.v4.app.Fragment;
import android.util.Log;

public class BaseFragment extends Fragment {
    public void log(String msg) {
        if (MlApplication.ISDEBUG)
            Log.e(this.getClass().getName(), msg);
    }
}
