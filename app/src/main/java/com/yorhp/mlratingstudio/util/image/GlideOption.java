package com.yorhp.mlratingstudio.util.image;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yorhp.mlratingstudio.R;

/**
 * Created by Tyhj on 2017/6/19.
 */

public class GlideOption {

    private volatile static RequestOptions options;

    public static RequestOptions getOption() {
        if (options == null) {
            synchronized (GlideOption.class) {
                if (options == null) {
                    options = new RequestOptions()
                            .placeholder(R.mipmap.ic_loading)
                            .error(R.mipmap.ic_loading)
                            .priority(Priority.NORMAL)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                }
            }
        }
        return options;
    }
}
