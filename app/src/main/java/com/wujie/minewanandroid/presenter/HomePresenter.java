package com.wujie.minewanandroid.presenter;


import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.contract.HomeContract;
import com.wujie.minewanandroid.http.RxObserver;
import com.wujie.minewanandroid.model.HomeModel;

/**
 * Created by HuangBin on 2018/11/16 12:41.
 * Description：
 */

public class HomePresenter extends BasePresenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {

    private final HomeModel mHomeModel;

    public HomePresenter() {
        mHomeModel = new HomeModel();
    }

    @Override
    public void getHomeList() {
        RxObserver<PageListDataBean<HomeBean>> rxObserver = new RxObserver<PageListDataBean<HomeBean>>(this) {
            @Override
            protected void start() {

            }

            @Override
            protected void onSuccess(PageListDataBean<HomeBean> homeBeanPageListDataBean) {
                if (homeBeanPageListDataBean.getDatas().size()==0) {
                    getView().showEmpty();
                }else {
                    getView().setHomeData(homeBeanPageListDataBean.getDatas());
                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                getView().showFailure(errorMsg);
            }
        };

        mHomeModel.getHomeList(rxObserver);

        addDisposable(rxObserver);
    }
}
