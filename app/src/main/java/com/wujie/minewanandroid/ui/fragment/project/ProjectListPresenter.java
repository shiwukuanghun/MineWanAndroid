package com.wujie.minewanandroid.ui.fragment.project;

import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.bean.ProjectItemBean;
import com.wujie.minewanandroid.http.BaseObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.RxHelper;

/**
 * Time：2019/1/15 0015 上午 11:46
 * Author：WuChen
 * Description：
 **/
public class ProjectListPresenter extends BasePresenter<ProjectListContact.View> implements ProjectListContact.Presenter {
    @Override
    public void getProjectList(int cid) {
        addDisposable(RxRetrofit.getApi()
                .getProjectList(cid)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<PageListDataBean<ProjectItemBean>>() {
                    @Override
                    protected void start() {
                        mV.showLoading("");
                    }

                    @Override
                    protected void onSuccess(PageListDataBean<ProjectItemBean> pageListDataBean) {
                        mV.hideLoading();
                        mV.getProjectList(pageListDataBean);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {
                        mV.showError();
                    }
                }));
    }
}
