package com.wujie.minewanandroid;

import android.content.Context;

import com.wujie.minewanandroid.util.AppConfig;
import com.wujie.minewanandroid.util.SpUtils;

/**
 * Created by HuangBin on 2018/12/1 20:48.
 * Description：
 */
public class AppContext {

    private static Context sContext;
    private static AppContext sInstance;

    private AppContext(Context context){
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    public static AppContext getInstance() {
        return sInstance;
    }

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (AppContext.class) {
                if (sInstance == null) {
                    sInstance = new AppContext(context.getApplicationContext());
                    AppConfig.init();
                    SpUtils.init(sContext);
                }
            }
        }
    }

}
