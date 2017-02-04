package com.kankan.qwwlogindemo.util;

import android.app.Application;
import android.content.Context;

/**
 * Author: liuyanguo
 * Date: 2017/2/4
 * Time: 15:57
 * Description:
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
