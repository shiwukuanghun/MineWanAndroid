package com.wujie.minewanandroid.ui.fragment.navigation;

import com.wujie.minewanandroid.bean.NavigationBean;
import com.wujie.minewanandroid.http.BaseObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.RxHelper;

import java.util.List;

/**
 * Time：2019/1/25 0025 下午 18:10
 * Author：WuChen
 * Description：
 **/
public class NavigationPresenter extends BasePresenter<NavigationContact.View> implements NavigationContact.Presenter {
    @Override
    public void getNavigation() {
        addDisposable(RxRetrofit.getApi()
                .getNavigation()
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<List<NavigationBean>>() {
                    @Override
                    protected void start() {
                        mV.showLoading("");
                    }

                    @Override
                    protected void onSuccess(List<NavigationBean> navigationBeans) {
                        mV.hideLoading();
                        mV.getNavigationSuccess(navigationBeans);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {
                        mV.showEmpty();
                    }
                }));
    }
}
