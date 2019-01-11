package com.wujie.minewanandroid.model;


import com.wujie.minewanandroid.bean.BaseBean;
import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.http.ApiServer;
import com.wujie.minewanandroid.http.RxObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.util.Constant;
import com.wujie.minewanandroid.util.RxHelper;

/**
 * Created by HuangBin on 2018/11/16 19:37.
 * Description：
 */

public class HomeModel {

    public void getHomeList(RxObserver<PageListDataBean<HomeBean>> rxObserver){
        RxRetrofit.createApi(ApiServer.class, Constant.BaseUrl)
                .getHomeList(0)
                .compose(RxHelper.<BaseBean<PageListDataBean<HomeBean>>>rxSchedulderHelper())
                .subscribe(rxObserver);
    }

}
