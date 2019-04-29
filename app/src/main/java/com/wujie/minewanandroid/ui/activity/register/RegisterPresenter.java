package com.wujie.minewanandroid.ui.activity.register;


import com.wujie.minewanandroid.http.BaseObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.RxHelper;

/**
 * Time：2019/1/16 0016 上午 10:09
 * Author：WuChen
 * Description：
 **/
public class RegisterPresenter extends BasePresenter<RegisterContact.View> implements RegisterContact.Presenter {
    @Override
    public void register(String username, String password, String repassword) {
        addDisposable(RxRetrofit.getApi()
                .register(username, password, repassword)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<Object>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(Object o) {
                        mV.registerSuccess();
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                }));
    }
}
