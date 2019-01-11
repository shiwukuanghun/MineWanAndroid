package com.wujie.commonmoudle;

import android.app.Application;

/**
 * Time：2019/1/11 0011 下午 14:00
 * Author：WuChen
 * Description：
 **/
public class BaseApplication extends Application {

    private static BaseApplication sBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApplication = this;
    }

    public static BaseApplication getBaseApplication() {
        return sBaseApplication;
    }
}
