package com.wujie.minewanandroid.ui.fragment.home;


import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.http.ApiServer;
import com.wujie.minewanandroid.http.BaseObserver;
import com.wujie.minewanandroid.http.RxObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.Constant;
import com.wujie.minewanandroid.util.RxHelper;

/**
 * Created by HuangBin on 2018/11/29 22:08.
 * Description：
 */
public class HomePresenter extends BasePresenter<HomeContact.View> implements HomeContact.Presenter {
    @Override
    public void getHomeList(int page) {
        RxObserver<PageListDataBean<HomeBean>> rxObserver = new RxObserver<PageListDataBean<HomeBean>>(this) {
            @Override
            protected void start() {
                mV.showLoading("");
            }

            @Override
            protected void onSuccess(PageListDataBean<HomeBean> homeBeanPageListDataBean) {
                if (homeBeanPageListDataBean != null && homeBeanPageListDataBean.getDatas() != null && homeBeanPageListDataBean.getDatas().size() > 0) {
                    mV.loadHomeData(homeBeanPageListDataBean);
                } else {
                    mV.showEmpty();
                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                if (errorCode == -1) {
                    mV.showError();
                } else {
                    mV.showFailure(errorMsg);
                }
            }
        };
        RxRetrofit.createApi(ApiServer.class, Constant.BaseUrl)
                .getHomeList(page)
                .compose(RxHelper.rxSchedulderHelper())
                .subscribe(rxObserver);
        addDisposable(rxObserver);
    }

    @Override
    public void collectOrUnCollectArticle(int id, int position) {
        addDisposable(RxRetrofit.getApi()
                .collectArticle(id)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleCollectResult())
                .subscribeWith(new BaseObserver<Object>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(Object o) {
                        mV.collectOrUnCollect(position);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {
                        mV.collectFailure(errorMsg);
                    }
                }));
    }
}
