package com.kankan.qwwlogindemo.util;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;

import java.io.File;

/**
 * Author: huangmingming
 * Date: 2016/12/22
 * Time: 18:46
 * Description:
 */

public class MyGlideModule implements com.bumptech.glide.module.GlideModule {

    public static final String DISK_CACHE_DIR_NAME = "ImageCache";
    public static final int DISK_CACHE_SIZE = 100 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File diskCacheDirPath = getDiskCacheDirPath();
                if (!diskCacheDirPath.exists()) {
                    diskCacheDirPath.mkdirs();
                }
                return DiskLruCacheWrapper.get(diskCacheDirPath, DISK_CACHE_SIZE);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

    private File getDiskCacheDirPath() {
        Context context = MyApplication.getContext();
        File cacheDir = null;
        if (context != null) {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                cacheDir = new File(context.getExternalCacheDir(), DISK_CACHE_DIR_NAME);
            } else {
                cacheDir = new File(context.getCacheDir(), DISK_CACHE_DIR_NAME);
            }
        }
        return cacheDir;
    }
}
