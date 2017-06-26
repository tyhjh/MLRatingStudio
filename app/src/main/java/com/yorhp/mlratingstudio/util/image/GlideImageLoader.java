package com.yorhp.mlratingstudio.util.image;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.imnjh.imagepicker.ImageLoader;
import com.yorhp.mlratingstudio.R;
import com.yorhp.mlratingstudio.app.MlApplication;

/*配合图片选择器使用*/

public class GlideImageLoader implements ImageLoader {
  @Override
  public void bindImage(ImageView imageView, Uri uri, int width, int height) {

    RequestOptions options = new RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_loading)
            .error(R.mipmap.ic_launcher)
            .override(width,height)
            .priority(Priority.HIGH)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    Glide.with(MlApplication.getAppContext()).load(uri).apply(options).into(imageView);
  }

  @Override
  public void bindImage(ImageView imageView, Uri uri) {
    Glide.with(MlApplication.getAppContext()).load(uri).apply(com.yorhp.mlratingstudio.util.image.GlideOption.getOption()).into(imageView);
  }

  @Override
  public ImageView createImageView(Context context) {
    ImageView imageView = new ImageView(context);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    return imageView;
  }

  @Override
  public ImageView createFakeImageView(Context context) {
    return new ImageView(context);
  }
}
