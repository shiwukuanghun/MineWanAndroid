package com.wujie.minewanandroid.util;

import com.wujie.minewanandroid.http.RxRetrofit;

/**
 * Time：2019/1/10 0010 下午 17:47
 * Author：WuChen
 * Description：
 **/
public class AppConfig {

    public static void init() {
        RxRetrofit.getInstance().initRetrofit(Constant.BaseUrl);
    }

}
