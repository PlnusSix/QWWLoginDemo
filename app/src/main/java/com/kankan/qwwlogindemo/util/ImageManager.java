package com.kankan.qwwlogindemo.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kankan.qwwlogindemo.R;

import java.io.File;

/**
 * Author: huangmingming
 * Date: 2016/12/22
 * Time: 18:43
 * Description: 项目中所有图片加载需要通过此封装使用
 */

public class ImageManager {

    private static ImageManager mInstance;
    private DiskCacheStrategy mDefaultCacheStrategy;
    private int mPlaceHolder;
    private int mErrorHolder;

    private ImageManager() {
        mDefaultCacheStrategy = DiskCacheStrategy.ALL;
        mPlaceHolder = R.mipmap.ic_launcher;
        mErrorHolder = R.mipmap.ic_launcher;
    }

    public static ImageManager get() {
        if (mInstance == null) {
            synchronized (ImageManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageManager();
                }
            }
        }
        return mInstance;
    }

    public void loadUrlIntoImageView(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadUrlIntoImageView(Activity activity, String url, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadUrlIntoImageView(Fragment fragment, String url, ImageView imageView) {
        Glide.with(fragment)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadUrlIntoImageView(android.support.v4.app.Fragment fragment, String url, ImageView imageView) {
        Glide.with(fragment)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadUrlIntoImageView(FragmentActivity fragmentActivity, String url, ImageView imageView) {
        Glide.with(fragmentActivity)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }


    public void loadFileIntoImageView(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadFileIntoImageView(Activity activity, File file, ImageView imageView) {
        Glide.with(activity)
                .load(file)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadFileIntoImageView(Fragment fragment, File file, ImageView imageView) {
        Glide.with(fragment)
                .load(file)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadFileIntoImageView(android.support.v4.app.Fragment fragment, File file, ImageView imageView) {
        Glide.with(fragment)
                .load(file)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadFileIntoImageView(FragmentActivity fragmentActivity, File file, ImageView imageView) {
        Glide.with(fragmentActivity)
                .load(file)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadResIntoImageView(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resId)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadResIntoImageView(Activity activity, int resId, ImageView imageView) {
        Glide.with(activity)
                .load(resId)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadResIntoImageView(Fragment fragment, int resId, ImageView imageView) {
        Glide.with(fragment)
                .load(resId)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadResIntoImageView(android.support.v4.app.Fragment fragment, int resId, ImageView imageView) {
        Glide.with(fragment)
                .load(resId)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadResIntoImageView(FragmentActivity fragmentActivity, int resId, ImageView imageView) {
        Glide.with(fragmentActivity)
                .load(resId)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(mPlaceHolder)
                .fallback(mPlaceHolder)
                .error(mErrorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }


    public void loadUrlIntoImageView(android.support.v4.app.Fragment fragment, String url, ImageView imageView, int placeHolder, int errorHolder) {
        if (placeHolder == 0) {
            placeHolder = mPlaceHolder;
        }
        if (errorHolder == 0) {
            errorHolder = mErrorHolder;
        }
        Glide.with(fragment)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(placeHolder)
                .fallback(placeHolder)
                .error(errorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadUrlIntoImageView(FragmentActivity fragmentActivity, String url, ImageView imageView, int placeHolder, int errorHolder) {
        if (placeHolder == 0) {
            placeHolder = mPlaceHolder;
        }
        if (errorHolder == 0) {
            errorHolder = mErrorHolder;
        }
        Glide.with(fragmentActivity)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(placeHolder)
                .fallback(placeHolder)
                .error(errorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }

    public void loadUrlIntoImageView(Activity activity, String url, ImageView imageView, int placeHolder, int errorHolder) {
        if (placeHolder == 0) {
            placeHolder = mPlaceHolder;
        }
        if (errorHolder == 0) {
            errorHolder = mErrorHolder;
        }
        Glide.with(activity)
                .load(url)
                .diskCacheStrategy(mDefaultCacheStrategy)
                .placeholder(placeHolder)
                .fallback(placeHolder)
                .error(errorHolder)
                .thumbnail(0.1f)
                .crossFade()
                .into(imageView);
    }


}
