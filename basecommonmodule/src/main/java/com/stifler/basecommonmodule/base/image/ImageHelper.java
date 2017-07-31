package com.stifler.basecommonmodule.base.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;

/**
 * Created by 7UP on 2017/7/27.
 */

public class ImageHelper {
    public static ImageHelper instance;
    public static Activity activity;
    public static Fragment fragment;
    public static GlideRequests glideRequests;
    public static GlideRequest glideRequest;

    public static ImageHelper getInstance() {
        if (instance == null) {
            instance = new ImageHelper();
        }
        return instance;
    }

    public ImageHelper with(Activity activity) {
        this.activity = activity;
        glideRequests = GlideApp.with(activity);
        return instance;
    }

    public ImageHelper with(Fragment fragment) {
        this.fragment = fragment;
        glideRequests = GlideApp.with(fragment);
        return instance;
    }

    public void clear(ImageView imageView) {
        glideRequests.clear(imageView);
    }

    public ImageHelper load(Object arg0) {
        glideRequest = glideRequests.load(arg0);
        return instance;
    }

    public ImageHelper fitCenter() {
        glideRequest = glideRequest.fitCenter();
        return instance;
    }

    public ImageHelper placeholder(int resourceId) {
        glideRequest = glideRequest.placeholder(resourceId);
        return instance;
    }

    public ImageHelper transform(Transformation<Bitmap> arg0) {
        glideRequest = glideRequest.transform(arg0);
        return instance;
    }

    public void into(ImageView imageView) {
        glideRequest.into(imageView);
    }

}
